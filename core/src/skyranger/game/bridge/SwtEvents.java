package skyranger.game.bridge;

import java.util.ArrayList;

public class SwtEvents {


	private static ArrayList<ISwtEvents> listeners = new ArrayList<ISwtEvents>();

	public static void addListener(ISwtEvents listener) {
		listeners.add(listener);
	}

	public static void removeListener(ISwtEvents listener) {
		listeners.remove(listener);
	}

	// mouse moved event
	private static void fireObjectAngeChanged(String id, float angle) {
		for (ISwtEvents listener : listeners) {
			listener.objectAngeChanged(id, angle);
		}
	}

	public static void changeObjectLanguage(String id, float angle) {
		fireObjectAngeChanged(id,angle);
	}

	
}
