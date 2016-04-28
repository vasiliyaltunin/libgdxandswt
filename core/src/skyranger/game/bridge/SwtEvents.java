package skyranger.game.bridge;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class SwtEvents {


	private static ArrayList<ISwtEvents> listeners = new ArrayList<ISwtEvents>();

	public static void addListener(ISwtEvents listener) {
		listeners.add(listener);
	}

	public static void removeListener(ISwtEvents listener) {
		listeners.remove(listener);
	}

	// angle of object changed
	private static void fireObjectAngeChanged(String id, float angle) {
		for (ISwtEvents listener : listeners) {
			listener.objectAngeChanged(id, angle);
		}
	}

	public static void changeObjectAngle(String id, float angle) {
		fireObjectAngeChanged(id,angle);
	}
	
	// position of object changed
	private static void fireChangeObjectPosition(String id, Vector2 position) {
		for (ISwtEvents listener : listeners) {
			listener.changeObjectPosition(id, position);
		}
	}
		
	public static void changeObjectPosition(String id, Vector2 position) {
		fireChangeObjectPosition(id,position);
		// TODO Auto-generated method stub
		
	}

	
}
