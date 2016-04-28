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

package skyranger.game.body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class BodySprite {

	private Body body;

	private World world;

	private SpriteBatch batch;

	private BodyDef bodyDef;
	private FixtureDef fixDef;

	private Sprite bodySprite;

	private Vector2 size;

	private float angle;

	private float angleDelta = 0;

	public BodySprite(World world, SpriteBatch batch) {
		this.create(world, batch, "assets\\null.png");
	}

	public BodySprite(World world, SpriteBatch batch, String fileName) {
		this.create(world, batch, fileName);
	}

	public void create(World world, SpriteBatch batch, String fileName) {
		this.bodyDef = new BodyDef();
		this.fixDef = new FixtureDef();

		this.world = world;
		this.batch = batch;

		this.size = new Vector2(1f, 1f);

		Texture tmpTex = new Texture(fileName);
		tmpTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.bodySprite = new Sprite(tmpTex);
		// this.bodySprite.setSize(1f,1f);
		this.bodySprite.setBounds(0f, 0f, this.size.x, this.size.y);
		this.bodySprite.setOrigin(this.bodySprite.getWidth() / 2,
				this.bodySprite.getHeight() / 2);
		this.bodySprite.setOriginCenter();

	}

	public Body getBody() {
		return this.body;
	}

	public void draw(Batch batch) {
		this.bodySprite.setPosition(
				this.body.getPosition().x - this.bodySprite.getWidth() / 2,
				this.body.getPosition().y - this.bodySprite.getHeight() / 2);
		this.bodySprite
				.setRotation((this.body.getAngle() * MathUtils.radiansToDegrees)
						- getAngleDelta());
		this.bodySprite.draw(this.batch);
	}

	public void createBody() {
		this.body = world.createBody(getBodyDef());
	}

	public BodyDef getBodyDef() {
		return bodyDef;
	}

	public FixtureDef getFixDef() {
		return fixDef;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void SetSize(float radius) {
		this.size = new Vector2(radius * 2, radius * 2);
		this.bodySprite.setBounds(0f, 0f, this.size.x, this.size.y);
	}

	public void SetSize(Vector2 size) {
		this.size = size;
		this.bodySprite.setBounds(0f, 0f, this.size.x, this.size.y);
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getAngleDelta() {
		return angleDelta;
	}

	public void setAngleDelta(float angleDelta) {
		this.angleDelta = angleDelta;
	}

}
