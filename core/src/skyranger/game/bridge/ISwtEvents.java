package skyranger.game.bridge;

import java.util.EventListener;

public interface ISwtEvents  extends EventListener {
	
	public void objectAngeChanged(String id, float angle);
	
}
