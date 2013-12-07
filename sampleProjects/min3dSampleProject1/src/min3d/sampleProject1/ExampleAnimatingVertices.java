package min3d.sampleProject1;

import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Rectangle;
import min3d.vos.Color4;
import min3d.vos.Number3d;
import android.graphics.Bitmap;

/**
 * Example of accessing and changing vertex data.
 * Specifically, we're animating the position of one vertex.
 * 
 * The same could be done for vertex colors, normal, or texture coordinates.
 * 
 * @author Lee
 */
public class ExampleAnimatingVertices extends RendererActivity
{
	Object3dContainer _plane;

	Number3d _defaultPosUL;
	Number3d _defaultPosLR;
	
	int _count;

	
	public void initScene() 
	{
		// Set size of the plane using the same aspect ratio of source image
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.deadmickey);
		float w = 2f;
		float h = w * (float)b.getHeight() / (float)b.getWidth();; 
		
		_plane = new Rectangle(w, h, 1,1, new Color4());
		_plane.doubleSidedEnabled(true); // ... so that the back of the plane is visible
		_plane.normalsEnabled(false);
		scene.addChild(_plane);

		Shared.textureManager().addTextureId(b, "mickey", false);
		_plane.textures().addById("mickey");
		
		b.recycle();

		//	Get the coordinates of the point at vertex number 0 (the upper-left vertex of the plane)
		//	and put its values in a Number3d. Same for lower-right vertex.
		_defaultPosUL = _plane.points().getAsNumber3d(0);
		_defaultPosLR = _plane.points().getAsNumber3d(3);

		_count = 0;
	}

	@Override 
	public void updateScene() 
	{
		// Change the values for the positions of the upperleft and lower right vertices
		
		float offset = (25f - (float)(_count % 25)) * 0.02f; // ... sure wish I knew of a nice Java tweener class
		offset *= offset;

		_plane.points().set(0, _defaultPosUL.x - offset, _defaultPosUL.y + offset, _defaultPosUL.z);

		offset = (25f - (float)((_count +13) % 25)) * 0.02f;
		offset *= offset;
		
		_plane.points().set(3, _defaultPosLR.x + offset, _defaultPosLR.y - offset, _defaultPosLR.z);
		
		_plane.rotation().y++;
		
		_count++;
	}
}
