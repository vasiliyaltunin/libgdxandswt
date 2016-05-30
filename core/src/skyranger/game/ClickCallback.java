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

import skyranger.game.bridge.GameMouseEvent;
import skyranger.game.objects.IBox2dObject;
import skyranger.game.objects.ObjectsManager;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

public class ClickCallback implements QueryCallback {

	private Vector3 lastClick;
	private Body hitBody;
	private PlayScreen playScreen;
	private String hitId;
	private IBox2dObject clickedObject;

	public ClickCallback(Vector3 lastClick, Body hitBody, PlayScreen playScreen) {
		super();
		this.lastClick = lastClick;
		this.hitBody = hitBody;
		this.playScreen=playScreen;
	}

	@Override
	public boolean reportFixture(Fixture fixture) {

		if (fixture.testPoint(getLastClick().x, getLastClick().y)) {

			for (IBox2dObject obj : ObjectsManager.getObjects().values()) {
				if (fixture.getBody() == obj.getBody()) {
					obj.setFixture(fixture);
					GameMouseEvent.mouseClickOnObject(obj);
					playScreen.setSelectedObjId(obj.getId());
					this.hitId=obj.getId();
					this.clickedObject = obj; 
					this.clickedObject.setAngle(this.clickedObject.getBody().getAngle()*MathUtils.radiansToDegrees);

				}
			}

			setHitBody(fixture.getBody());

			return false;
		} else
			return true;

	}

	public Vector3 getLastClick() {
		return lastClick;
	}

	public void setLastClick(Vector3 lastClick) {
		this.lastClick = lastClick;
	}

	public Body getHitBody() {
		return hitBody;
	}

	public void setHitBody(Body hitBody) {
		this.hitBody = hitBody;
	}
	
	public String getHitId()
	{
		return this.hitId;
	}
	
	public IBox2dObject getObject()
	{
		return this.clickedObject;
	}

}
