package skyranger.game.bridge;

import java.util.EventListener;

import com.badlogic.gdx.math.Vector2;

public interface ISwtEvents  extends EventListener {
	
	public void objectAngeChanged(String id, float angle);

	public void changeObjectPosition(String id, Vector2 position);
	
}
