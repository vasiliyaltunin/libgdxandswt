/*******************************************************************************
 *     Copyright 2016 Vasiliy Altunin aka SkyRanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package skyranger.game;

import org.lwjgl.Sys;

import skyranger.game.bridge.GameMouseEvent;
import skyranger.game.bridge.ISwtEvents;
import skyranger.game.bridge.SwtEvents;
import skyranger.game.objects.IBox2dObject;
import skyranger.game.objects.ObjectsManager;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.QueryCallback;

public class InputController implements InputProcessor {

	private PlayScreen screen;

	private ClickCallback clickCallback;

	private Vector3 lastClick = new Vector3();

	private Body hitBody;

	private boolean isLeftShift = false;
	
	private Body selectedObject = null;

	private Vector3 prevDragPos = new Vector3();

	// We init all we need and add listeners
	public InputController(PlayScreen playScreen) {
		this.screen = playScreen;

		this.clickCallback = new ClickCallback(this.lastClick, this.hitBody, playScreen);

		SwtEvents.addListener(new ISwtEvents() {

			@Override
			public void objectAngeChanged(String id, float angle) {
				// angle=angle+5;
				ObjectsManager.get(id).setAngle(angle);
				ObjectsManager
						.get(id)
						.getBody()
						.setTransform(
								ObjectsManager.get(id).getBody().getPosition(),
								angle * MathUtils.degreesToRadians);
			}

			@Override
			public void changeObjectPosition(String id, Vector2 position) {
				ObjectsManager.get(id).setPosition(position);
				ObjectsManager
						.get(id)
						.getBody()
						.setTransform(position,
								ObjectsManager.get(id).getBody().getAngle());
			}

			@Override
			public void changeObjectSize(String id, Vector2 size) {
				ObjectsManager.get(id).setSize(size);
				ObjectsManager.get(id).recreateBodyObject();
			}
		});
	}

	// Manage all key down events
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.W:
			this.screen.movement.y = this.screen.speed;
			break;
		case Keys.A:
			this.screen.movement.x = -this.screen.speed;
			break;
		case Keys.S:
			this.screen.movement.y = -this.screen.speed;
			break;
		case Keys.D:
			this.screen.movement.x = this.screen.speed;
			break;
		case Keys.SHIFT_LEFT:
			this.isLeftShift = true;
			break;
		default:
			break;
		}
		return true;
	}

	// Manage all key up events
	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.W:
			this.screen.movement.y = 0;
			break;
		case Keys.S:
			this.screen.movement.y = 0;
			break;
		case Keys.A:
			this.screen.movement.x = 0;
			break;
		case Keys.D:
			this.screen.movement.x = 0;
			break;
		case Keys.F12:
			this.screen.isDebug = !this.screen.isDebug;
			break;
		case Keys.SHIFT_LEFT:
			this.isLeftShift = false;
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	// Manage all mouse clicks
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// left
		if (button == 0) {
			GameMouseEvent.leftMouseClicked();

			lastClick.x = screenX;
			lastClick.y = screenY - 0.1f;
			this.screen.camera.unproject(lastClick);

			//if it just left click on object we run check if any object under click area
			if (!this.isLeftShift)
			{			
				hitBody = null;

				this.screen.getWorld().QueryAABB(clickCallback, lastClick.x,
						lastClick.y, lastClick.x, lastClick.y);
			}
			else
			{
				System.out.println(lastClick);
			}
		}
		// middle
		if (button == 2) {
			GameMouseEvent.middleMouseClicked();
		}
		// right
		if (button == 1) {
			this.screen.mouseMove.rightMouseClicked();
			this.screen.mouseLook = true;
		}

		return true;
	}

	// Manage all mouse button up events
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		this.screen.mouseLook = false;
		GameMouseEvent.mouseReleased();
		prevDragPos = new Vector3();
		return true;
	}

	// Manage all mouse drag events
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		//if right mouse button pressed we act as mouse look
		if (this.screen.mouseLook) {
			Vector3 cameraV3 = this.screen.camera.unproject(new Vector3(
					screenX, screenY, 0));

			Vector2 cameraV2 = new Vector2(cameraV3.x, cameraV3.y);

			// Take the vector that goes from body origin to mouse in camera
			// space
			Vector2 bodyPos = this.screen.player.getBody().getPosition();
			Vector2 newCamVector = cameraV2.sub(bodyPos);

			// Now you can set the angle;
			this.screen.player.getBody().setTransform(
					this.screen.player.getBody().getPosition(),
					newCamVector.angleRad());

		}

		//if left shift pressed and RMB not pressed and we do some mouse drag we move selected object
		if ((this.isLeftShift) && (!this.screen.mouseLook) && (prevDragPos.x>0) && (prevDragPos.y>0))
		{
			
			Vector3 dragPos = new Vector3(screenX, screenY,0);  
			
			this.screen.camera.unproject(dragPos);
			this.screen.camera.unproject(prevDragPos);

			Vector3 delta = new Vector3(dragPos.x-prevDragPos.x,dragPos.y-prevDragPos.y,0);
			
			IBox2dObject obj = this.clickCallback.getObject();
			
			Vector2 objPos = obj.getPosition();
			
			Vector3 newpos = new Vector3(objPos.x+delta.x,objPos.y+delta.y,0);
						
			SwtEvents.changeObjectPosition(obj.getId(), new Vector2(newpos.x,newpos.y));
			
			this.clickCallback.getObject().setAngle(this.clickCallback.getObject().getBody().getAngle()*MathUtils.radiansToDegrees);

			GameMouseEvent.mouseClickOnObject(obj);
			
			GameMouseEvent.mouseMoved(new Vector2(screenX, screenY));
			
		}

		prevDragPos  = new Vector3(screenX, screenY,0);
		
		return false;
	}

	// Manage mouse move event
	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		GameMouseEvent.mouseMoved(new Vector2(screenX, screenY));

		return true;
	}

	// Manage mouse scroll event
	@Override
	public boolean scrolled(int amount) {
		if (!this.isLeftShift) {
			this.screen.mouseMove.mouseWheelMoved(amount);
			this.screen.camera.zoom += amount / 25f;
			return true;
		}
		else
		{
			this.clickCallback.getObject().setAngle(this.clickCallback.getObject().getBody().getAngle()*MathUtils.radiansToDegrees);
			SwtEvents.changeObjectAngle(this.clickCallback.getObject().getId(), this.clickCallback.getObject().getAngle()+amount);
			GameMouseEvent.mouseClickOnObject(this.clickCallback.getObject());
		}
		return true;
	}

}
