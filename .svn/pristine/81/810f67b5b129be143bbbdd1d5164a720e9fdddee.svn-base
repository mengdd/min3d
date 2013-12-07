package min3d.sampleProject1;

import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.HollowCylinder;
import min3d.vos.TextureVo;
import android.graphics.Bitmap;

/**
 * Demonstrates setting U/V texture wrapping 
 * (TextureVo.repeatU and TextureVo.repeatV)
 * 
 * @author Lee
 */
public class ExampleTextureWrap extends RendererActivity
{
	Object3dContainer _object;
	TextureVo _texture;
	int _counter;
	
	public void initScene() 
	{
		_object = new HollowCylinder(1f, 0.5f, 0.66f, 25);
		_object.normalsEnabled(false);
		_object.vertexColorsEnabled(false);
		scene.addChild(_object);
		
		Bitmap b = Utils.makeBitmapFromResourceId(R.drawable.uglysquares);
		Shared.textureManager().addTextureId(b, "texture", true);
		b.recycle();
		
		_texture = new TextureVo("texture");
		
		_object.textures().add(_texture);
		
		_counter = 0;
	}

	@Override 
	public void updateScene() 
	{
		_object.rotation().y = (float)(Math.sin(_counter*0.02f) * 45);
		
		if (_counter % 40 == 0) 
		{
			_texture.repeatU = ! _texture.repeatU;
		}
		if (_counter % 80 == 0) 
		{
			_texture.repeatV = ! _texture.repeatV;
		}
		
		_counter++;
	}
}
