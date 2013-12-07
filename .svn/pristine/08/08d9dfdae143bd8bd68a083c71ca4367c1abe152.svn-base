package min3d.sampleProject1;

import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.vos.Light;
import android.graphics.Bitmap;

/**
 * Demonstrates visual difference between a texture with mipmapping versus a texture without.
 * The cube without the mipmapped texture displays distracting, aliasing artifacts.  
 * 
 * Note how mipmapping is set on or off at the TextureManager level.   
 * (It is not controlled at the Object3d level, or the TextureVO level)
 * 
 * Mipmapping requires the target hardware to support OpenGL ES 1.1.
 * If it does not, the request to generate the MIP maps is ignored.
 */
public class ExampleMipMap extends RendererActivity
{
	Object3dContainer _holder;
	Object3dContainer _cubeWithMipMap;
	Object3dContainer _cubeWithoutMipMap;
	
	public void initScene() 
	{
		scene.lights().add(new Light());
		
		_holder = new Object3dContainer(0, 0);
		scene.addChild(_holder);
		
		_cubeWithMipMap = new Box(1.5f,1.5f,1.5f);
		_cubeWithMipMap.position().y = 1f;
		_holder.addChild(_cubeWithMipMap);

		_cubeWithoutMipMap = new Box(1.5f,1.5f,1.5f);
		_cubeWithoutMipMap.position().y = -1f;
		_holder.addChild(_cubeWithoutMipMap);
		
		//

		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.checkerboard);
		
		Shared.textureManager().addTextureId(b, "checkerboard_with_mipmap", true);
		Shared.textureManager().addTextureId(b, "checkerboard_without_mipmap", false);

		_cubeWithMipMap.textures().addById("checkerboard_with_mipmap");
		_cubeWithoutMipMap.textures().addById("checkerboard_without_mipmap");

		b.recycle();
	}
	
	@Override 
	public void updateScene() 
	{
		_holder.rotation().y +=1;
		_holder.rotation().z +=.25;
	}
}
