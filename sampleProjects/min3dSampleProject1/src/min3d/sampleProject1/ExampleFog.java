package min3d.sampleProject1;

import min3d.Shared;
import min3d.Utils;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.objectPrimitives.Rectangle;
import min3d.vos.Color4;
import min3d.vos.Light;
import android.graphics.Bitmap;

public class ExampleFog extends RendererActivity {
	private Box[] boxes;
	
	@Override
	public void initScene() {
		Light light = new Light();
    	scene.lights().add(light);
    	scene.camera().position.x = 0;
    	scene.camera().position.y = 0;
    	scene.camera().position.z = 10;
    	
		Bitmap b = Utils.makeBitmapFromResourceId(R.drawable.barong);
		Shared.textureManager().addTextureId(b, "barong", false);
		b.recycle();
		
		b = Utils.makeBitmapFromResourceId(R.drawable.wood);
		Shared.textureManager().addTextureId(b, "wood", false);
		b.recycle();
    	
		boxes = new Box[5];
		
		for(int i=0; i<5; i++)
		{
			Box box = new Box(1, 1, 1);
			box.position().x = (float) (-4 + ( Math.random() * 8));
			box.position().y = (float) (-4 + ( Math.random() * 8));
			box.position().z = (i + 1) * -8;
			box.textures().addById("barong");
			box.vertexColorsEnabled(false);
			boxes[i] = box;
	   		scene.addChild(box);
		}
   		
   		Color4 planeColor = new Color4(255, 255, 255, 255);
		Rectangle east = new Rectangle(40, 12, 2, 2, planeColor);
		Rectangle west = new Rectangle(40, 12, 2, 2, planeColor);
		Rectangle up = new Rectangle(40, 12, 2, 2, planeColor);
		Rectangle down = new Rectangle(40, 12, 2, 2, planeColor);
   		
		east.position().x = -6;
		east.rotation().y = -90;
		east.position().z = -20;
		east.lightingEnabled(false);
		east.textures().addById("wood");
		
		west.position().x = 6;
		west.rotation().y = 90;
		west.position().z = -20;
		west.lightingEnabled(false);
		west.textures().addById("wood");
		
		up.rotation().x = -90;
		up.rotation().z = 90;
		up.position().y = 6;
		up.position().z = -20;
		up.lightingEnabled(false);
		up.textures().addById("wood");
		
		down.rotation().x = 90;
		down.rotation().z = 90;
		down.position().y = -6;
		down.position().z = -20;
		down.lightingEnabled(false);
		down.textures().addById("wood");

   		scene.addChild(east);
   		scene.addChild(west);
   		scene.addChild(up);
   		scene.addChild(down);
   		
   		scene.fogColor(new Color4(0, 0, 0, 255) );
   		scene.fogNear(10);
   		scene.fogFar(40);
   		scene.fogEnabled(true);
	}
	
	@Override 
	public void updateScene() 
	{
		for(int i=0; i<5; i++)
		{
			Box box = boxes[i];
			box.position().z += .25;
			box.rotation().x++;
			box.rotation().y++;
			if( box.position().z > scene.camera().position.z)
				box.position().z = -40;
		}
	}
}