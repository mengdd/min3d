package min3d.sampleProject1;

import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import android.view.MotionEvent;

/**
 * @author Lee
 */
public class ExampleCamera extends RendererActivity
{
	private float _dx;
	private float _dy;
	private float _rot;
	
	public void initScene() 
	{
		Object3dContainer cube;
		
		cube = new Box(1.5f,1.5f,1.5f);
		cube.position().y = 0.75f;
		cube.normalsEnabled(false);
		scene.addChild(cube);
		
		cube = new Box(0.8f,0.8f,0.8f);
		cube.position().y = 0.4f;
		cube.position().x = 1.3f;
		cube.position().z = 1.3f;
		cube.rotation().y = 45;
		cube.normalsEnabled(false);
		scene.addChild(cube);
		
		// .. wow nice composition
		
		_dx = 0.0001f; // :)
	}

	@Override 
	public void updateScene() 
	{
		if (_dx != 0)
		{
			/**
			 * When trackball moves horizontally... 
			 * 
			 * Rotate camera around center of the scene in a circle.
			 * Its position is 2 units above the boxes, but its target() position 
			 * remains at the scene origin, so the camera always looks towards the center. 
			 */
			
			_rot += _dx * 50f;
			float x = (float)Math.sin(_rot*Utils.DEG) * 5f;
			float z = (float)Math.cos(_rot*Utils.DEG) * 5f;
			scene.camera().position.setAll(x,2,z);
			
			_dx = 0;
		}
		
		if (_dy != 0)
		{
			// When trackball moves vertically, change camera's frustum "shortSideLength" property 
			// This effectively changes field of view. (??)
			
			float len = scene.camera().frustum.shortSideLength() + _dy;
			if (len < 0.1f) len = 0.1f;
			if (len > 50f) len = 50f;
			scene.camera().frustum.shortSideLength(len);
			
			_dy = 0;
		}
	}

	@Override
	public boolean onTrackballEvent(MotionEvent $e)
	{
		_dx = $e.getX();
		_dy = $e.getY();
		return true;
	}
}