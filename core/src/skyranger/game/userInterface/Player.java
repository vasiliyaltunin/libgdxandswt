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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends BodySprite {

	public Player(World world, SpriteBatch batch, String fileName) {
		super(world, batch, fileName);

		this.getBodyDef().position.set(0f, 0f);
		this.getBodyDef().type = BodyType.DynamicBody;

		// top-down gravity simulation
		this.getBodyDef().linearDamping = 2f; // 0 no dumping
		this.getBodyDef().angularDamping = 2f;

		// ball shape
		CircleShape playerSphape = new CircleShape();
		playerSphape.setPosition(new Vector2(0, 0));
		playerSphape.setRadius(0.5f);

		this.SetSize(0.5f);

		// fixture def
		this.getFixDef().shape = playerSphape;
		this.getFixDef().density = 80f; // kg weight
		this.getFixDef().friction = 0.5f; // 1 dont slide - 0 - like ice
		this.getFixDef().restitution = 0.9f; // elastivness jump up 1 - jump
												// forever / 0 - not jump at all

		createBody();
		this.getBody().createFixture(getFixDef());

		this.setAngleDelta(90);

		playerSphape.dispose();
	}

}
