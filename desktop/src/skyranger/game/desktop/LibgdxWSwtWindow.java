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

package skyranger.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import skyranger.game.MainGameClass;

public class LibgdxWSwtWindow {

	private static MainGameClass mainClass;

	public LibgdxWSwtWindow(MainGameClass mainGameClass) {
		super();
		mainClass = mainGameClass;
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.samples = 4; 
		new LwjglApplication(mainClass, config);

		try {
			MainWindow window = new MainWindow(mainClass);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
