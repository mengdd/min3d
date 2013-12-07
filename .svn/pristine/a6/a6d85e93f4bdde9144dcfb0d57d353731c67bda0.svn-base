package min3d.sampleProject1;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Box;
import min3d.objectPrimitives.Sphere;
import min3d.vos.Light;
import min3d.vos.LightType;
import min3d.vos.Number3d;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Light properties explorer.
 * Position, direction, spotCutoff, spotExpo, type, visibility... 
 * 
 * Can also be used as a testbed for other parameter-related testing. 
 * 
 * @author Lee
 */
public class ExampleLightProperties extends RendererActivity 
{
	Object3dContainer _rect;
	Object3dContainer _obj;
	Light _light;
	
	
	@Override
	protected void onCreateSetContentView()
	{
		setContentView(R.layout.lightproperties_layout);
		
		// Add OpenGL surface
        LinearLayout ll = (LinearLayout) this.findViewById(R.id.scene1Holder);
        ll.addView(_glSurfaceView);
	}
	
	@Override
    public void onInitScene()
	{
        // Add seekbar listeners
        int[] ids = { R.id.seekbarA1, R.id.seekbarA2, R.id.seekbarA3, R.id.seekbarB1, R.id.seekbarB2, R.id.seekbarB3, R.id.seekbarC1, R.id.seekbarC2 };

        for (int id : ids)
        {
            SeekBar b;
            b = (SeekBar) this.findViewById(id);
            b.setProgress(50);
            b.setOnSeekBarChangeListener(seekChange);
        }
        
        // Add checkbox listeners
        int[] ids2 = { R.id.cbx1, R.id.cbx2 };
        
        for (int id : ids2)
        {
            CheckBox ck = (CheckBox) this.findViewById(id);
            ck.setOnCheckedChangeListener(checkChange);
        }
        
        // Set some defaults
        CheckBox c1 = (CheckBox) this.findViewById(R.id.cbx1);
        c1.setChecked(true);
        
        CheckBox c2 = (CheckBox) this.findViewById(R.id.cbx2);
        c2.setChecked(true);
        
        SeekBar sB3 = (SeekBar) this.findViewById(R.id.seekbarB3);
        sB3.setProgress(100); // pointing away from camera
        
        SeekBar sAngle = (SeekBar) this.findViewById(R.id.seekbarC1);
        sAngle.setProgress(33); // some angle
        
        SeekBar sExp = (SeekBar) this.findViewById(R.id.seekbarC2);
        sExp.setProgress(45); // cutoff
	}
	
    OnSeekBarChangeListener seekChange = new OnSeekBarChangeListener() 
    {
    	public void onProgressChanged(SeekBar $s, int $progress, boolean $touch)
    	{
	    	float n = (float)$progress/100f;
	    	n = (n - 0.5f) * 2f;
	    	
	    	switch($s.getId())
	    	{
	    		case R.id.seekbarA1:
	    			_light.position.setX(n*3);
	    			break;
	    		case R.id.seekbarA2:
	    			_light.position.setY(n*3);
	    			break;
	    		case R.id.seekbarA3:
	    			_light.position.setZ(n*3); // +3 + n*6);
	    			break;
	    			
	    		case R.id.seekbarB1:
	    			//_light.spotDirection.setX(n);
	    			scene.camera().position.x = n*10;
	    			break;
	    		case R.id.seekbarB2:
	    			//_light.spotDirection.setY(n);
	    			scene.camera().position.y = n*10;
	    			break;
	    		case R.id.seekbarB3:
	    			//_light.spotDirection.setZ(n);
	    			scene.camera().position.z = n*10;
	    			break;
	    			
	    		case R.id.seekbarC1:
	    			n += 1; //[0,2]
	    			n *= 45+4;
	    			if (n > 90) n = 180;
	    			_light.spotCutoffAngle(n);
	    			Log.v("x", "cutoff " + n);
	    			break;
	    			
	    		case R.id.seekbarC2:
	    			n += 1; //[0,2]
	    			n *= 22; // [0,44]
	    			n -= 4; // [-4,40]
	    			if (n < 0) n = 0;
	    			_light.spotExponent(n);
	    			Log.v("x", "exp " + n);
	    			break;
	    	}

	    	Log.v("x", "campos" + scene.camera().position.toString());

	    	if (_light.position.isDirty())
	    	{
	    		Log.v("x", "pos " + _light.position.toString());	    		
	    	}
	    	
	    	if (_light.direction.isDirty())
	    	{
	    		Number3d n3 = _light.direction.toNumber3d();
	    		n3.normalize();
	    		_light.direction.setAllFrom(n3);
	    		Log.v("x", "dir " + _light.direction.toString());
	    	}
    	}
    	
    	
    	public void onStartTrackingTouch(SeekBar $s)
    	{
    		
    	}
    	public void onStopTrackingTouch(SeekBar $s)
    	{
    		
    	}
    };
    
    OnCheckedChangeListener checkChange = new OnCheckedChangeListener() 
    {
		public void onCheckedChanged(CompoundButton $ck, boolean isChecked) 
		{
			switch($ck.getId())
			{
				case R.id.cbx1:
					if (isChecked)
						_light.type(LightType.DIRECTIONAL);
					else
						_light.type(LightType.POSITIONAL);
					Log.v("x", "light is now " + _light.type() + _light.type().glValue());
					break;
					
				case R.id.cbx2:
					_light.isVisible(isChecked);
					Log.v("x", "light visible: " + _light.isVisible());
					break;
			}
		}
	};
    
    //
	
	public void initScene() 
	{
		scene.backgroundColor().setAll(0xff222222);

		_light = new Light();
		_light.position.setAll(0, 0, +3);
		_light.diffuse.setAll(255, 255, 255, 255);
		_light.ambient.setAll(0, 0, 0, 0);
		_light.specular.setAll(0, 0, 0, 0);
		_light.emissive.setAll(0, 0, 0, 0);
		scene.lights().add(_light);

		_rect = new Box(6, 6, .1f);
		_rect.position().z = -3;
		scene.addChild(_rect);
		
		_obj = new Sphere(1.5f, 20,20);
		scene.addChild(_obj);
	}

	@Override 
	public void updateScene() 
	{
		//_rect.rotation().y+=0.33;
	}
	
	@Override 
	public void onUpdateScene()
	{
	}
	
}
