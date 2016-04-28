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

package skyranger.game.xml;

import java.io.IOException;

import skyranger.game.objects.ObjectsManager;
import skyranger.game.objects.Wall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.XmlReader;

public class XmlParser {

	XmlReader.Element root;

	public XmlParser() {
		super();
		try {
			XmlReader xmlReader = new XmlReader();
			FileHandle file = Gdx.files.internal("assets\\map.xml");
			this.root = xmlReader.parse(file);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parseMapLevel(World world, SpriteBatch batch) {
		String name = root.getAttribute("id");
		System.out.println(name);

		// get all static objects on level
		XmlReader.Element staticElement = root.getChildByName("static");

		int statCount = staticElement.getChildCount();
		System.out.println("Static cnt=" + statCount);

		for (int i = 0; i < statCount; ++i) {
			XmlReader.Element taskElement = staticElement.getChild(i);
			String id = taskElement.getAttribute("id");
			String type = taskElement.getAttribute("type");
			float x = taskElement.getFloat("x", 1f);
			float y = taskElement.getFloat("y", 1);
			float w = taskElement.getFloat("w", 1);
			float h = taskElement.getFloat("h", 1);
			int angle = taskElement.getInt("angle", 1);
			String texture = taskElement.getAttribute("texture");

			if (type.equals("wall")) {
				Wall tmpWall = new Wall(id, new Vector2(x, y),
						new Vector2(w, h), angle);
				tmpWall.createBodyObject(world, batch, texture);

				ObjectsManager.add(id, tmpWall);
			}
			// System.out.println("id=" + id + ", x=" + x + ", y=" + y + ", hw="
			// + hw + ", hh=" + hh + ", angle=" + angle+","+texture);

		}
	}

}
