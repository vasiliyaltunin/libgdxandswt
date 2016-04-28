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

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import skyranger.game.bridge.GameMouseEvent;
import skyranger.game.objects.IBox2dObject;
import skyranger.game.objects.ObjectsManager;
import skyranger.game.objects.Wall;
import skyranger.game.userInterface.Cursor;
import skyranger.game.userInterface.Player;
import skyranger.game.xml.XmlParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PlayScreen implements Screen {

	private static final float TIMESTEP = 1 / 60f;
	private static final int VELOCITYITERATIONS = 8;
	private static final int POSITIONITERATIONS = 3;
	private static final int PPM = 25;

	// private static final Vector2 movement;

	private World world;
	private Box2DDebugRenderer debugRenderer;
	public OrthographicCamera camera;

	public Body box;

	public float speed = 500f;
	public Vector2 movement = new Vector2(0, 0);

	public Player player;
	private Cursor cursor;

	public boolean mouseLook;

	private SpriteBatch batch = new SpriteBatch();
	private org.lwjgl.input.Cursor emptyCursor;

	public boolean isDebug = true;

	public GameMouseEvent mouseMove = new GameMouseEvent();

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);

		// if (!movement.isZero())
		// {
		// System.out.println(movement);
		// }

		this.player.getBody().applyForceToCenter(movement, true);

		camera.position.set(this.player.getBody().getPosition(), 0);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		player.draw(batch);
		cursor.draw(camera, player);
		for (IBox2dObject obj : ObjectsManager.getObjects().values()) {
			((Wall) obj).getBodySprite().draw(batch);
		}

		batch.end();

		if (Mouse.isInsideWindow()) {
			try {
				Mouse.setNativeCursor(emptyCursor);
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// Gdx.input.setCursorCatched(true);
			try {
				Mouse.setNativeCursor(null);
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (isDebug) {

			this.debugRenderer.render(this.world, this.camera.combined);
		}

	}

	@Override
	public void resize(int width, int height) {
		this.camera.viewportWidth = width / 25;
		this.camera.viewportHeight = height / 25;
		camera.update();
	}

	private void setupMouse() {
		int min = org.lwjgl.input.Cursor.getMinCursorSize();
		IntBuffer tmp = BufferUtils.createIntBuffer(min * min);
		try {
			emptyCursor = new org.lwjgl.input.Cursor(min, min, min / 2,
					min / 2, 1, tmp, null);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void show() {

		XmlParser pars = new XmlParser();

		setupMouse();

		this.world = new World(new Vector2(0f, 0f), true);

		this.debugRenderer = new Box2DDebugRenderer();

		this.camera = new OrthographicCamera(Gdx.graphics.getWidth() / PPM,
				Gdx.graphics.getHeight() / PPM);

		Gdx.input.setInputProcessor(new InputController(this));

		pars.parseMapLevel(this.world, this.batch);

		// BodyDef bodyDef = new BodyDef();
		// FixtureDef fixDef = new FixtureDef();

		player = new Player(world, batch, "img\\player.png");

		cursor = new Cursor(world, batch, "img\\cursor.png", player.getBody());

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		this.world.dispose();
		this.debugRenderer.dispose();
	}

	public World getWorld() {
		return world;
	}

}
