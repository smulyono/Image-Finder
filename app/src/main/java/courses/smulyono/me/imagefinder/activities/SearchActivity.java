package courses.smulyono.me.imagefinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import courses.smulyono.me.imagefinder.R;
import courses.smulyono.me.imagefinder.adapters.ImageResultsAdapter;
import courses.smulyono.me.imagefinder.models.ImageResult;


public class SearchActivity extends ActionBarActivity {
    private final String APP_TAG = "[IMAGE_FINDER]";
    private final String GOOGLE_SEARCH_URL = "https://ajax.googleapis.com/ajax/services/search/images";
    
    private Button btnSearch;
    private EditText etQuery;
    private GridView gvResults;
    private ArrayList<ImageResult> imageResults;
    private ImageResultsAdapter aImageResults;
    private SearchView mSearchView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        // Displaying action bar icon again
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_isearch);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // register the elements
        setupViews();
    }
    
    private void setupViews(){

        gvResults = (GridView) findViewById(R.id.gvResults);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageResult result = aImageResults.getItem(position);
                // launch the other activity 
                Intent i = new Intent(SearchActivity.this, ImageDisplayActivity.class);
                // pass image results url
                i.putExtra("imgResult", result);
                // launch
                startActivity(i);
            }
        });
        
        imageResults = new ArrayList<ImageResult>();
        
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);
    }

    // button search click
    private void onImageSearch(String queryText){
        Toast.makeText(this, queryText, Toast.LENGTH_SHORT).show();

        RequestParams params = new RequestParams();
        params.put("q", queryText);
        params.put("v", "1.0");
        params.put("rsz", 8);
        // image size
//        params.put("imgsz", "small");
        
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(GOOGLE_SEARCH_URL, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(APP_TAG, response.toString());
                // clear out the older arraylist
                try {
                    JSONArray imageResultsJSON = response.getJSONObject("responseData").getJSONArray("results");
                    // only run this during initial search
                    aImageResults.clear();
                    aImageResults.addAll(ImageResult.fromJSONArray(imageResultsJSON));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(APP_TAG, imageResults.size() + "" );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                onImageSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        switch (id){
            case R.id.action_filter : 
                return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
