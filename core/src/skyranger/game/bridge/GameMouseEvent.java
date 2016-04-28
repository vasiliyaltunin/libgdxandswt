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

import java.util.ArrayList;

import skyranger.game.objects.IBox2dObject;

import com.badlogic.gdx.math.Vector2;

public class GameMouseEvent {

	private static ArrayList<IGameMouseListener> listeners = new ArrayList<IGameMouseListener>();

	public static void addListener(IGameMouseListener listener) {
		listeners.add(listener);
	}

	public static void removeListener(IGameMouseListener listener) {
		listeners.remove(listener);
	}

	// mouse moved event
	private static void fireMousMoved(Vector2 position) {
		for (IGameMouseListener listener : listeners) {
			listener.mouseMoved(position);
		}
	}

	public static void mouseMoved(Vector2 position) {
		fireMousMoved(position);
	}

	// left mouse button clicked
	private static void fireLeftClick() {
		for (IGameMouseListener listener : listeners) {
			listener.leftMouseClicked();
		}
	}

	public static void leftMouseClicked() {
		fireLeftClick();
	}

	// middle mouse button clicked
	private static void fireMiddleClick() {
		for (IGameMouseListener listener : listeners) {
			listener.middleMouseClicked();
		}
	}

	public static void middleMouseClicked() {
		fireMiddleClick();
	}

	// right mouse button clicked
	private static void fireRightClick() {
		for (IGameMouseListener listener : listeners) {
			listener.rigthMouseClicked();
		}
	}

	public void rightMouseClicked() {
		fireRightClick();
	}

	// mouse button released
	private static void fireMouseRelease() {
		for (IGameMouseListener listener : listeners) {
			listener.mouseReleased();
		}
	}

	public static void mouseReleased() {
		fireMouseRelease();
	}

	public void mouseWheelMoved(int amount) {
		fireMouseWheelMoved(amount);

	}

	private static void fireMouseWheelMoved(int amount) {
		for (IGameMouseListener listener : listeners) {
			listener.mouseWheelMoved(amount);
		}
	}

	public static void mouseClickOnObject(IBox2dObject obj) {
		fireMouseClickOnObject(obj);
	}

	private static void fireMouseClickOnObject(IBox2dObject obj) {
		for (IGameMouseListener listener : listeners) {
			listener.mouseClickOnObject(obj);
		}
	}

	// return new Vector2(Gdx.input.getX(),Gdx.input.getY());

}
