package courses.smulyono.me.imagefinder.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by smulyono on 2/28/15.
 */
public class ImageResult implements Serializable {
    public String thumbUrl;
    public String fullUrl;
    public String title;
    
    // new ImageResult(.. raw item json)
    public ImageResult(JSONObject json){
        try {
            this.fullUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    
    public static ArrayList<ImageResult> fromJSONArray(JSONArray array){
        ArrayList<ImageResult> results = new ArrayList<ImageResult>();
        for (int i=0; i < array.length(); i++){
            try {
                results.add(new ImageResult(array.getJSONObject(i)));
            } catch (JSONException ev){
                ev.printStackTrace();
            }
        }
        return results;
    }
}
