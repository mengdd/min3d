package min3d.sampleProject1;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;


/**
 * Main menu activity
 * 
 * @author Lee
 */
public class SplashActivity extends ListActivity  
{
	private final int CONTEXTID_VIEWFILE = 0;
	private final int CONTEXTID_CANCEL = 1;
	
	private String _baseSourcePath = "http://code.google.com/p/min3d/source/browse/trunk/sampleProjects/min3dSampleProject1/src/min3d/sampleProject1/"; 
	
	class ItemVo
	{
		public String filename;
		public Class<?> cls;
		public String label;

		public ItemVo(String $label, Class<?> $class, String $filename)
		{
			label = $label;
			cls = $class;
			filename = $filename;
		}
	}	
	
	private ItemVo[] _items = {
			new ItemVo("\"Hello, Jupiter\"", ExampleRotatingPlanets.class, "ExampleRotatingPlanets.java"),
			new ItemVo("Minimal example", ExampleMostMinimal.class, "ExampleMostMinimal.java"),
			new ItemVo("Vertex colors", ExampleVertexColors.class, "ExampleVertexColors.java"),
			new ItemVo("Texture", ExampleTextures.class, "ExampleTextures.java"),
			new ItemVo("Usage of Vertices class", ExampleVerticesVariations.class, "ExampleVerticesVariations.java"),
			new ItemVo("Triangles, lines, points", ExampleRenderType.class, "ExampleRenderType.java"),
			new ItemVo("Camera, frustum (trackball)", ExampleCamera.class, "ExampleCamera.java"),
			new ItemVo("Multiple lights", ExampleMultipleLights.class, "ExampleMultipleLights.java"),
			new ItemVo("Animating vertices", ExampleAnimatingVertices.class, "ExampleAnimatingVertices.java"),
			new ItemVo("Rendering subset of faces", ExampleSubsetOfFaces.class, "ExampleSubsetOfFaces.java"),
			new ItemVo("Assigning textures dynamically", ExampleAssigningTexturesDynamically.class, "ExampleAssigningTexturesDynamically.java"),
			new ItemVo("MIP Mapping (on vs. off)", ExampleMipMap.class, "ExampleMipMap.java"),
			new ItemVo("Texture wrapping", ExampleTextureWrap.class, "ExampleTextureWrap.java"),
			new ItemVo("Multiple textures", ExampleMultiTexture.class, "ExampleMultiTexture.java"),
			new ItemVo("Texture offset", ExampleTextureOffset.class, "ExampleTextureOffset.java"),
			new ItemVo("3D inside layout", ExampleInsideLayout.class, "ExampleInsideLayout.java"),
			new ItemVo("Fog Example", ExampleFog.class, "ExampleFog.java"),
			new ItemVo("Transparent GL Surface", ExampleTransparentGlSurface.class, "TransparentActivity.java"),
			new ItemVo("Load model from .obj file", ExampleLoadObjFile.class, "ExampleLoadObjFile.java"),
			new ItemVo("Load multiple models from .obj file", ExampleLoadObjFileMultiple.class, "ExampleLoadObjFileMultiple.java"),
			new ItemVo("Load model from .3ds file", ExampleLoad3DSFile.class, "ExampleLoad3DSFile.java"),
			new ItemVo("Load animated .md2 file", ExampleLoadMD2File.class, "ExampleLoadMD2File.java"),
			new ItemVo("Keyframe animation", ExampleKeyframeAnimation.class, "ExampleKeyframeAnimation.java"),
			new ItemVo("Using the accelerometer", ExampleAccelerometer.class, "ExampleAccelerometer.java")
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	String[] strings = new String[_items.length];
    	for (int i = 0; i < _items.length; i++) {
    		strings[i] = _items[i].label;
    	}
    	
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
	    setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));
	    
	    TextView tv = (TextView) this.findViewById(R.id.splashTitle);
	    Linkify.addLinks(tv, 0x07);
	    
	    registerForContextMenu(getListView());	    
	    
	    // TEST ONLY:
    	// this.startActivity( new Intent(this, ExampleTransparentGlSurface.class ) );
    }
    
    @Override
    public void onListItemClick(ListView parent, View v, int position, long id)
    {
    	this.startActivity( new Intent(this, _items[position].cls ) );
    }
    
    //
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        super.onCreateOptionsMenu(menu);

        int i = 0;
        menu.add(0, 0, i++, "project home");
        menu.add(0, 1, i++, "author blog");

        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
    	Intent i;
    	
        switch (item.getItemId()) 
        {
            case 0:
            	i = new Intent(Intent.ACTION_VIEW);
            	i.setData(Uri.parse( this.getResources().getString(R.string.projectUrl) ));
            	startActivity(i);                
            	return true;
                
            case 1:
            	i = new Intent(Intent.ACTION_VIEW);
            	i.setData(Uri.parse( this.getResources().getString(R.string.myBlogUrl) ));
            	startActivity(i);                
            	return true;
        }
        return false;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) 
    {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, CONTEXTID_VIEWFILE, 0, "View source on Google Code");
		menu.add(0, CONTEXTID_CANCEL, 0, "Cancel");
    }

    @Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		
		switch (item.getItemId()) 
		{
			case CONTEXTID_VIEWFILE:
            	Intent i = new Intent(Intent.ACTION_VIEW);
            	String url = _baseSourcePath + _items[ (int)info.id ].filename;
            	i.setData(Uri.parse(url));
            	startActivity(i);                
				return true;
			case CONTEXTID_CANCEL:
				// do nothing
				return true;
				
			default:
				return super.onContextItemSelected(item);
		}
	}    
}
