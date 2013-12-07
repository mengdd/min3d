package min3d.sampleProject1;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.vos.Light;

/**
 * Most minimal example I could think of.
 * 
 * @author Lee
 */
public class ExampleMostMinimal extends RendererActivity
{
	Object3dContainer _cube;
	
	public void initScene() 
	{
		/*
		 * Add a light to the Scene.
		 * The Scene must have light for Object3d's with normals  
		 * enabled (which is the default setting) to be visible.
		 */
		scene.lights().add( new Light() );
		
		/*
		 *  Create an Object3d and add it to the scene.
		 *  In this case, we're creating a cube using the Box class, which extends Object3d.
		 *  Any Object3d must be declared with booleans that determine whether its vertices store: 
		 *  	(a) U/V texture coordinates 
		 *  	(b) Normals (required for shading based on light source/s)
		 *  	(c) Per-vertex color information 
		 *  We're going to create a shaded cube without textures or colors, so for those arguments
		 *  we are using "false,true,false".  
		 */
		_cube = new Box(1,1,1, null, true,true,false);
		
		/*
		 * 	Since we're not using any colors on the cube, we're setting this to false.  
		 * (False is the default)
		 */
		_cube.colorMaterialEnabled(false);
		
		/*
		 * Add cube to the scene.
		 */
		scene.addChild(_cube);
	}

	@Override 
	public void updateScene() 
	{
		/*
		 * Do any manipulation of scene properties or to objects in the scene here.
		 */
		_cube.rotation().y++;
	}
}
