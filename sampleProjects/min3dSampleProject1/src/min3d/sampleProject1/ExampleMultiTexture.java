package min3d.sampleProject1;

import javax.microedition.khronos.opengles.GL10;

import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.objectPrimitives.Sphere;
import min3d.vos.Light;
import min3d.vos.TexEnvxVo;
import min3d.vos.TextureVo;
import android.graphics.Bitmap;

/**
 * This is the most complex example, for now.
 * 
 * Demonstrates:
 * - How to add multiple textures to an object (2, in this case)
 * - How to change 'texture environent' settings on a texture 
 * 
 * In this example, a sphere has an image of jupiter as its first texture.
 * Its second texture is a plain white image with a transparent 'hole' in the middle.
 * We'll see what's the effect of applying different texture environment 'blend modes'
 * to the second texture.  
 *  
 * @author Lee
 */
public class ExampleMultiTexture extends RendererActivity
{
	Object3dContainer _cube;
	Object3dContainer _sphere;
	
	TextureVo _jupiterTexture;
	TextureVo _alphaTexture;
	
	TexEnvxVo _alphaTextureEnv;
	
	int _count = 0;
	
	public void initScene() 
	{
		Light light = new Light();
		light.ambient.setAll(0xff888888);
		light.position.setAll(3,0,3);
		scene.lights().add(light);

		// Create objects
		
		// * Note:
		// ------- 
		// While the order in which objects are drawn is not important in terms of
		// z-ordering (which is taken care of automatically by OpenGL), the order 
		// _is_ important when it comes to transparency. Here, for the transparency
		// of the "_jupiter" sphere to be apparent, it must added as the second child
		// of the scene (which means it will be drawn second). The engine does not 
		// manage this automatically for you.

		_cube = new Box(1.3f,1.3f,1.3f);
		_cube.position().x = +0.4f;
		_cube.normalsEnabled(false);
		scene.addChild(_cube);

		_sphere = new Sphere(.8f, 12, 9);
		_sphere.position().x = -0.2f;
		_sphere.position().z = 1f;
		scene.addChild(_sphere);

		// Add textures in TextureManager
		
		Bitmap b;
		
		b = Utils.makeBitmapFromResourceId(R.drawable.jupiter);
		Shared.textureManager().addTextureId(b, "jupiter", false);
		b.recycle();
		
		b = Utils.makeBitmapFromResourceId(R.drawable.white_with_alpha_hole);
		Shared.textureManager().addTextureId(b, "alpha", false);
		b.recycle();
		
		// Add textures to sphere
		_sphere.textures().addById("jupiter");
		TextureVo texture = _sphere.textures().addById("alpha");
		
		// We saved a reference to the sphere's "alpha" texture above so we can target 
		// its texture environment VO, which we will change around in the loop below.
		_alphaTextureEnv = texture.textureEnvs.get(0);
		
		_count = 0;
	}
	
	@Override 
	public void updateScene() 
	{
		_cube.rotation().x -= .4f;
		_cube.rotation().y -= .6f;
		_sphere.rotation().y += 3;
		
		// Cycle thru the texture environment 'blend modes' just so
		// we can see what they all look like...
		
		if (_count % 1000 == 0) {
			_alphaTextureEnv.setAll(GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE); // .. default
		}
		else if (_count % 1000 == 200) {
			_alphaTextureEnv.setAll(GL10.GL_TEXTURE_ENV_MODE, GL10.GL_BLEND);
		}
		else if (_count % 1000 == 400) {
			_alphaTextureEnv.setAll(GL10.GL_TEXTURE_ENV_MODE, GL10.GL_ADD);
		}
		else if (_count % 1000 == 600) {
			// Note, this is the correct setting if you're just interested  
			// in layering one bitmap that has some transparency on top of another,
			// without altering the color or alpha of the either.
			_alphaTextureEnv.setAll(GL10.GL_TEXTURE_ENV_MODE, GL10.GL_DECAL);
		}
		else if (_count % 1000 == 800) {
			_alphaTextureEnv.setAll(GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);
		}
			
		_count++;
		
		
		// *
		//
		// Lastly, note that TextureVo holds not just one TextureEnvVo, but an ArrayList of them. 
		// So you can send a series of "glTexEnvx" commands, as if you were typing them out  
		// manually, to create more complex blending/layering effects.
		//
		// See http://www.khronos.org/opengles/sdk/1.1/docs/man/glTexEnv.xml
	}
}
