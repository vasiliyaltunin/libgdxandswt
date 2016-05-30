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

public class BasicObject implements IBox2dObject {

	public final static short STATIC_OBJECT = 0x0001;
	public final static short PLAYER_OBJECT = 0x0002;
	
	protected BodySprite body;

	private String id;

	private Vector2 position;

	private Vector2 size;

	private float angle;

	private Fixture fixture;
	
	private final short categoryBit = -1;
	private final short maskBit = -1;

	public BasicObject(String id, Vector2 position, Vector2 size, float angle) {
		super();
		this.id = id;
		this.position = position;
		this.size = size;
		this.setAngle(angle);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Vector2 getSize() {
		return size;
	}

	@Override
	public void setSize(Vector2 size) {
		this.size=size;
	}
	
	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public void setPosition(Vector2 pos) {
		this.position=pos;
	}

	
	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public void setAngle(float angle) {
		this.angle = angle;
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

	@Override
	public void recreateObject() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recreateBodyObject() {
		// TODO Auto-generated method stub
		
	}

	public short getCategoryBit() {
		return categoryBit;
	}

	public short getMaskBit() {
		return maskBit;
	}
	
}
