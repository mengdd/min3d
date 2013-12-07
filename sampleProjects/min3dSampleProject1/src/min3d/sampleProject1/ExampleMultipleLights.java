package min3d.sampleProject1;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.objectPrimitives.Sphere;
import min3d.vos.Light;
import min3d.vos.LightType;

/**
 * @author Lee
 */
public class ExampleMultipleLights extends RendererActivity
{
	private final float DEGREE = (float)(Math.PI/180);

	Object3dContainer _sphere;
	
	Light _lightRed;
	Light _lightGreen;
	Light _lightBlue;
	Object3dContainer _boxRed;
	Object3dContainer _boxGreen;
	Object3dContainer _boxBlue;

	int _count = 0;


	public void initScene() 
	{
		scene.camera().position.setAll(0,0,5);

		// Add three lights
		
		_lightRed = new Light();
		_lightRed.ambient.setAll(0x88110000);
		_lightRed.diffuse.setAll(0xffff0000);
		_lightRed.type(LightType.POSITIONAL);
		scene.lights().add(_lightRed);

		_lightGreen = new Light();
		_lightGreen.ambient.setAll(0x88001100);
		_lightGreen.diffuse.setAll(0xff00ff00);
		_lightGreen.type(LightType.POSITIONAL);
		scene.lights().add(_lightGreen);

		_lightBlue = new Light();
		_lightBlue.ambient.setAll(0x88000011);
		_lightBlue.diffuse.setAll(0xff0000ff);
		_lightBlue.type(LightType.POSITIONAL); 
		scene.lights().add(_lightBlue);

		// The objects that we'll light up
		
		_sphere = new Sphere(1.0f, 20, 15);
		_sphere.vertexColorsEnabled(false);
		scene.addChild(_sphere);
		
		// Boxes, displayed just as a visualization aid
		
		_boxRed = new Box(0.05f,0.05f,0.05f);
		_boxRed.normalsEnabled(false);
		_boxRed.vertexColorsEnabled(false);
		_boxRed.defaultColor().setAll(0xaaff0000);
		scene.addChild(_boxRed);
		
		_boxGreen = new Box(0.07f,0.07f,0.07f);
		_boxGreen.normalsEnabled(false);
		_boxGreen.vertexColorsEnabled(false);
		_boxGreen.defaultColor().setAll(0xaa00ff00);
		scene.addChild(_boxGreen);

		_boxBlue = new Box(0.05f,0.05f,0.05f);
		_boxBlue.normalsEnabled(false);
		_boxBlue.vertexColorsEnabled(false);
		_boxBlue.defaultColor().setAll(0xaa0000ff);
		scene.addChild(_boxBlue);

		_count = 0;
	}
	
	@Override 
	public void updateScene() 
	{
		// Set lights' position
		
		_lightRed.position.setAll(0, +0.9f, 2.5f);
		float x = (float)(Math.sin(_count%360 * DEGREE) * -1.5f);
		float z = (float)(Math.cos(_count%360 * DEGREE) * 2.25f);
		_lightGreen.position.setAll(x, 0, z);
		_lightBlue.position.setAll(0, -0.9f, 2.5f);
		
		// Sync boxes' position with lights
		
		_boxRed.position().setAllFrom(_lightRed.position.toNumber3d());
		_boxGreen.position().setAllFrom(_lightGreen.position.toNumber3d());
		_boxBlue.position().setAllFrom(_lightBlue.position.toNumber3d());

		// Red light pulses by changing diffuse red property
		
		short mag = (short)(255 - (_count % 60) * (255/60));
		_lightRed.diffuse.r(mag);
		_boxRed.defaultColor().r = mag;

		// Green light blinks on and off using isVisible() property
		
		if (_count % 30 == 0) {
			_lightGreen.isVisible(true);
			_boxGreen.isVisible(true);
		}
		else if (_count % 30 == 20) {
			_lightGreen.isVisible(false);
			_boxGreen.isVisible(false);
		}

		_sphere.rotation().y += 1;
		_count++;
	}
}
