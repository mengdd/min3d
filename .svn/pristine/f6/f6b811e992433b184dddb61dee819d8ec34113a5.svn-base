package min3d.sampleProject1;

import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.vos.Color4;
import min3d.vos.Light;
import android.graphics.Bitmap;

/**
 * Demonstrates use of Object3D's constructor parameters "useUvs", "useNormals", and "useColors".
 * These params are passed to the Object3D's MeshData instance to determine whether the object's
 * vertices will include texture coordinate, normal, and color data (By default, itwill).
 * 
 * It's useful to set these to false when you know you won't need them,  
 * for the sake of memory and performance. 
 * 
 * Note that the Object3d properties "texturesEnabled", "normalsEnabled", and "colorsEnabled" 
 * are only applicable when the Object3d is created with "useUvs", "useNormals", and "useColors"
 * respectively are set to true. 
 * 
 * @author Lee
 */
public class ExampleVerticesVariations extends RendererActivity
{
	Object3dContainer _cube1;
	Object3dContainer _cube2;
	Object3dContainer _cube3;
	Object3dContainer _cube4;
	
	public void initScene() 
	{
		scene.lights().add(new Light());
		
		Color4[] colors = new Color4[6];
		colors[0] = new Color4(255,0,0,255);
		colors[1] = new Color4(0,255,0,255);
		colors[2] = new Color4(0,0,255,255);
		colors[3] = new Color4(255,255,0,255);
		colors[4] = new Color4(0,255,255,255);
		colors[5] = new Color4(255,0,255,255);

		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.uglysquares);
		Shared.textureManager().addTextureId(b, "uglysquares", false);
		b.recycle();

		// _cube1's vertices contain color data, but not U/V or normal data.
		// The result is an unshaded box, with a different color on each side.

		_cube1 = new Box(.6f,.6f,.6f, colors,  false,false,true);
		_cube1.normalsEnabled(false);
		_cube1.position().y = 1.2f;

		// _cube2's verticies contain uv data, but not normal or color data.
		// The result is an unshaded box with a texture on each side.
		
		_cube2 = new Box(.6f,.6f,.6f, colors,  true,false,false);
		_cube2.position().y = .4f;
		_cube2.textures().addById("uglysquares");
		
		// _cube3's verticies contain uv and normal data, but not color data  
		// The result is an shaded box with a texture on each side.
		// The Scene must of course contain a light for the object to be visible.
		
		_cube3 = new Box(.6f,.6f,.6f, null,  true,true,false);
		_cube3.position().y = -.4f;
		_cube3.textures().addById("uglysquares");
		
		// _cube4's verticies contain neither uv, normal, or color data (in other words, just position data) 
		// Since it does not use per-vertex color data, the Object3's defaultColor property
		// will dictate the object's color.
		// The result is an unshaded box which is one solid color (red);
		
		_cube4 = new Box(.6f,.6f,.6f, colors,  false,false,false);
		_cube4.defaultColor().setAll(0xffff0000);
		_cube4.position().y = -1.2f;

		scene.addChild(_cube1);
		scene.addChild(_cube2);
		scene.addChild(_cube3);
		scene.addChild(_cube4);
	}

	@Override 
	public void updateScene() 
	{
		_cube1.rotation().y++;
		_cube2.rotation().y++;
		_cube3.rotation().y++;
		_cube4.rotation().y++;
	}
}
