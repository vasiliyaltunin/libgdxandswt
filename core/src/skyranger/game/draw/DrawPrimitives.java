package skyranger.game.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

public class DrawPrimitives {

	
	private static ShapeRenderer lineRenderer = new ShapeRenderer();

    public static void DrawBox(Vector2 leftBottomCorner, Vector2 rectSize, float linesize, Color color, Matrix4 matrix, float angle)
    {
        Gdx.gl.glLineWidth(linesize);
        lineRenderer.setProjectionMatrix(matrix);
        lineRenderer.begin(ShapeRenderer.ShapeType.Line);
        lineRenderer.setColor(color);
        
        lineRenderer.rect(leftBottomCorner.x-rectSize.x/2,leftBottomCorner.y-rectSize.y/2,rectSize.x/2,rectSize.y/2,rectSize.x,rectSize.y,1,1,angle);

        lineRenderer.end();
        Gdx.gl.glLineWidth(0.5f);
    }

}
