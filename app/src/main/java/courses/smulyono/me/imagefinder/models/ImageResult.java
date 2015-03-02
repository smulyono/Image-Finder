package courses.smulyono.me.imagefinder.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by smulyono on 2/28/15.
 */
public class ImageResult implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.thumbUrl);
        dest.writeString(this.fullUrl);
        dest.writeString(this.title);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.tbWidth);
        dest.writeInt(this.tbHeight);
    }

    private ImageResult(Parcel in) {
        this.thumbUrl = in.readString();
        this.fullUrl = in.readString();
        this.title = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.tbWidth = in.readInt();
        this.tbHeight = in.readInt();
    }

    public static final Parcelable.Creator<ImageResult> CREATOR = new Parcelable.Creator<ImageResult>() {
        public ImageResult createFromParcel(Parcel source) {
            return new ImageResult(source);
        }

        public ImageResult[] newArray(int size) {
            return new ImageResult[size];
        }
    };
}
