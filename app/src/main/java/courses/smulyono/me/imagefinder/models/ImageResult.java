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
    public int width;
    public int height;
    public int tbWidth;
    public int tbHeight;
    
    // new ImageResult(.. raw item json)
    public ImageResult(JSONObject json){
        try {
            this.fullUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("title");
            this.width = json.getInt("width");
            this.height = json.getInt("height");
            this.tbHeight = json.getInt("tbHeight");
            this.tbWidth = json.getInt("tbWidth");
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
