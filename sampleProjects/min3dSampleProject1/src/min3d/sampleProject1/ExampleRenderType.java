package min3d.sampleProject1;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Sphere;
import min3d.vos.RenderType;

/**
 * @author Lee
 */
public class ExampleRenderType extends RendererActivity
{
	Object3dContainer _object;
	int _count;
	
	public void initScene() 
	{
		_object = new Sphere(1f, 15, 10);
		_object.normalsEnabled(false); // .. allows vertex colors to show through 
		scene.addChild(_object);
		
		// ( btw, notice how the Scene contains no Lights)

		_count = 0;
	}
	
	@Override 
	public void updateScene() 
	{
		if (_count % 300 == 0) {
			_object.renderType(RenderType.TRIANGLES);
		}
		else if (_count % 300 == 100) {
			_object.renderType(RenderType.POINTS);
			_object.pointSize(1f);
		}
		else if (_count % 300 == 200) {
			_object.renderType(RenderType.LINES);
			_object.lineWidth(1.0f);
		}
		
		_object.pointSize( _object.pointSize()+0.12f );
		_object.lineWidth( _object.lineWidth()+0.12f );
		
		_object.rotation().y +=1;
		_object.rotation().z += 0.2f;

		_count++;
	}
}
