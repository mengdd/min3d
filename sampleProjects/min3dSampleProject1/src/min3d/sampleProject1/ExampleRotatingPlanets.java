package min3d.sampleProject1;

import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Sphere;
import min3d.vos.Light;
import android.graphics.Bitmap;

/**
 * This is the "demo" example.
 * It shows how to add children to Object3dContainers.  
 * 
 * If you're familiar with Flash, this is similar to adding DisplayObjects to the displaylist.
 * 
 * If you're familiar with Papervision3D or Away3D for Flash, this is similar to using 
 * DisplayObject3D's or Object3D's to the Scene.
 * 
 * @author Lee
 */
public class ExampleRotatingPlanets extends RendererActivity
{
	Object3dContainer _jupiter;
	Object3dContainer _earth;
	Object3dContainer _moon;
	int _count;
	
	public void initScene() 
	{
		Light light = new Light();
		
		light.ambient.setAll((short)64, (short)64, (short)64, (short)255);
		light.position.setAll(3, 3, 3);
		scene.lights().add(light);
		
		// Add Jupiter to scene
		_jupiter = new Sphere(0.8f, 15, 10, true,true,false);
		scene.addChild(_jupiter);

		// Add Earth as a child of Jupiter
		_earth = new Sphere(0.4f, 12, 9, true,true,false);
		_earth.position().x = 1.6f;
		_earth.rotation().x = 23;
		_jupiter.addChild(_earth);
 
		// Add the Moon as a child of Earth
		_moon = new Sphere(0.2f, 10, 8,  true,true,false);
		_moon.position().x = 0.6f;
		_earth.addChild(_moon);

		// Add textures to TextureManager		
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.jupiter);
		Shared.textureManager().addTextureId(b, "jupiter", false);
		b.recycle();

		b = Utils.makeBitmapFromResourceId(this, R.drawable.earth);
		Shared.textureManager().addTextureId(b, "earth", false);
		b.recycle();
			
		b = Utils.makeBitmapFromResourceId(this, R.drawable.moon);
		Shared.textureManager().addTextureId(b, "moon", false);
		b.recycle();

		// Add textures to objects based on on the id's we assigned the textures in the texture manager
		_jupiter.textures().addById("jupiter");
		_earth.textures().addById("earth");
		_moon.textures().addById("moon");
		
		_count = 0;
	}
	
	@Override 
	public void updateScene() 
	{
		// Spin spheres
		_jupiter.rotation().y += 1.0f;
		_earth.rotation().y += 3.0f;
		_moon.rotation().y -= 12.0f;
		
		// Wobble Jupiter a little just for fun 
		_count++;
		float mag = (float)(Math.sin(_count*0.2*Utils.DEG)) * 15;
		_jupiter.rotation().z = (float)Math.sin(_count*.33*Utils.DEG) * mag;
		
		// Move camera around
		scene.camera().position.z = 4.5f + (float)Math.sin(_jupiter.rotation().y * Utils.DEG);
		scene.camera().target.x = (float)Math.sin((_jupiter.rotation().y + 90) * Utils.DEG) * 0.8f;
	}
}
