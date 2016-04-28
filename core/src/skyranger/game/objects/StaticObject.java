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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class StaticObject implements IBox2dObject {

	protected BodySprite body;

	private String id;

	private Vector2 position;

	private Vector2 size;

	private float angle;

	private Fixture fixture;

	public StaticObject(String id, Vector2 position, Vector2 size, float angle) {
		super();
		this.id = id;
		this.position = position;
		this.size = size;
		this.angle = angle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skyranger.game.objects.Box2dObject#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skyranger.game.objects.Box2dObject#getPosition()
	 */
	@Override
	public Vector2 getPosition() {
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skyranger.game.objects.Box2dObject#getSize()
	 */
	@Override
	public Vector2 getSize() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skyranger.game.objects.Box2dObject#getAngle()
	 */
	@Override
	public float getAngle() {
		return angle;
	}

	public BodySprite getBodySprite() {
		return body;
	}

	@Override
	public Body getBody() {
		return body.getBody();
	}

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}
	
}
