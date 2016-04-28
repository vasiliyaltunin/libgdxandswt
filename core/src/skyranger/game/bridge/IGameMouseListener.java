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

package skyranger.game.bridge;

import java.util.EventListener;

import skyranger.game.objects.IBox2dObject;

import com.badlogic.gdx.math.Vector2;

public interface IGameMouseListener extends EventListener {

	public void mouseMoved(Vector2 position);

	public void leftMouseClicked();

	public void middleMouseClicked();

	public void rigthMouseClicked();

	public void mouseReleased();

	public void mouseWheelMoved(int amount);

	public void mouseClickOnObject(IBox2dObject obj);

}