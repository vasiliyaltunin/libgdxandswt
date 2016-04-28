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

package skyranger.game.objects;

import skyranger.game.body.BodySprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Wall extends StaticObject {

	public Wall(String id, Vector2 position, Vector2 size, float angle) {
		super(id, position, size, angle);
		// TODO Auto-generated constructor stub
	}

	public void createBodyObject(World world, SpriteBatch batch, String fileName) {

		this.body = new BodySprite(world, batch, fileName);

		this.body.getBodyDef().position.set(this.getPosition().x,
				this.getPosition().y);
//		this.body.getBodyDef().position.set(0,0);
		this.getBodySprite().getBodyDef().type = BodyType.DynamicBody;
		// top-down gravity simulation
		this.getBodySprite().getBodyDef().linearDamping = 100f; // 0 no dumping
		this.getBodySprite().getBodyDef().angularDamping = 100f;
		this.getBodySprite().getBodyDef().awake = true;

		PolygonShape wallSphape = new PolygonShape();
		
		wallSphape.setAsBox(this.getSize().x / 2, this.getSize().y / 2);
				
//		wallSphape.setAsBox(this.getSize().x / 2, this.getSize().y / 2,
//				new Vector2(this.getPosition().x, this.getPosition().y),
//				this.getAngle() * MathUtils.degRad);

		// fixture def
		this.getBodySprite().getFixDef().shape = wallSphape;
		this.getBodySprite().getFixDef().density = 1000000f; // kg weight
		this.getBodySprite().getFixDef().friction = 1f; // 1 dont slide - 0 -
														// like ice
		this.getBodySprite().getFixDef().restitution = 0f; // elastivness jump
															// up 1 - jump
															// forever / 0 - not
															// jump at all

		this.getBodySprite().createBody();

		
		this.body.getBody().setTransform(this.getPosition(),this.getAngle() * MathUtils.degRad);

		this.getBodySprite().getBody()
				.createFixture(this.getBodySprite().getFixDef());

		wallSphape.dispose();

	}

}
