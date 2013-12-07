package min3d.sampleProject1;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.HollowCylinder;

/**
 * Example of rendering a 'subset' of index buffer list (list of triangle faces)
 * 
 * @author Lee
 */
public class ExampleSubsetOfFaces extends RendererActivity
{
	Object3dContainer _cylinder;
	
	int _numFaces;
	int _faceIndexStart;
	int _faceIndexLength;
	int _incrementer;
	
	
	public void initScene() 
	{
		_cylinder = new HollowCylinder(1f, 0.5f, 0.66f, 25); 
		_cylinder.scale().setAll(1.2f,1.2f,1.2f);
		_cylinder.normalsEnabled(false);
		_cylinder.vertexColorsEnabled(true);
		_cylinder.doubleSidedEnabled(true);
		scene.addChild(_cylinder);
		
		_cylinder.faces().renderSubsetEnabled(true);

		_numFaces = _cylinder.faces().size();
		_faceIndexStart = 0;
		_faceIndexLength = 0;
		_incrementer = +2;		
	}
	
	@Override 
	public void updateScene() 
	{
		// Update the parameters for rendering subset of cylinder's faces
		_cylinder.faces().renderSubsetStartIndex(_faceIndexStart);
		_cylinder.faces().renderSubsetLength(_faceIndexLength);
		_faceIndexLength += _incrementer;

		if (_faceIndexLength >= _numFaces-1) _incrementer = -2;
		if (_faceIndexLength <= 0+1) _incrementer = +2;
		
		_cylinder.rotation().y += 1.5;
	}
}
