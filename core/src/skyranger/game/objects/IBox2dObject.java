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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface IBox2dObject {

	public abstract String getId();

	public abstract Vector2 getPosition();

	public abstract void setPosition(Vector2 pos);
	
	public abstract Vector2 getSize();

	public abstract void setSize(Vector2 size);

	public abstract float getAngle();

	public void setAngle(float angle);
	
	public abstract Body getBody();

	public Fixture getFixture();

	public void setFixture(Fixture fixture);

	public void recreateObject();

	public abstract void recreateBodyObject();
	
}