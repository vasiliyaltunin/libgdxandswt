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

package skyranger.game.userInterface;

import skyranger.game.body.BodySprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Cursor extends BodySprite {

	private Vector2 mousePosition;

	@SuppressWarnings("unused")
	private Body player;

	@SuppressWarnings("unused")
	private World world;

	public Cursor(World world, SpriteBatch batch, String fileName, Body player) {
		super(world, batch, fileName);

		this.getBodyDef().position.set(0f, 0f);
		this.getBodyDef().type = BodyType.DynamicBody;
		// top-down gravity simulation
		this.getBodyDef().linearDamping = 0f; // 0 no dumping
		this.getBodyDef().angularDamping = 0f;
		this.getBodyDef().awake = false;

		// ball shape

		PolygonShape cursorSphape = new PolygonShape();
		cursorSphape.setAsBox(0.5f, 0.5f);

		// fixture def
		this.getFixDef().shape = cursorSphape;
		this.getFixDef().density = 0f; // kg weight
		this.getFixDef().friction = 0f; // 1 dont slide - 0 - like ice
		this.getFixDef().restitution = 0f; // elastivness jump up 1 - jump
											// forever / 0 - not jump at all
		this.getFixDef().isSensor = true;

		createBody();
		this.getBody().createFixture(getFixDef());

		cursorSphape.dispose();

		this.player = player;

	}

	Vector2 unproject(Camera cam, Vector2 vector) {
		Vector3 tmp = new Vector3();
		tmp.set(vector.x, vector.y, 0f);
		cam.unproject(tmp);
		vector.set(tmp.x, tmp.y);
		return vector;
	}

	Vector2 getMousePosition() {
		return new Vector2(Gdx.input.getX(), Gdx.input.getY());
	}

	public void draw(Camera camera, Player player) {
		super.draw(this.getBatch(),camera);

		this.mousePosition = this.getMousePosition();

		this.mousePosition = unproject(camera, this.mousePosition);

		// Adjust to let to corner
		this.mousePosition.x += 0.5;
		this.mousePosition.y -= 0.5;

		this.getBody().setTransform(this.mousePosition,
				0 * MathUtils.degreesToRadians);

	}

}
