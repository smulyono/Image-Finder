package courses.smulyono.me.imagefinder.models;

import android.app.Activity;

import java.io.Serializable;

import courses.smulyono.me.imagefinder.R;

/**
 * Created by smulyono on 2/28/15.
 */
public class ImageFilter implements Serializable{
    public String[] imageSizeOptions;
    public String[] colorFilterOptions;
    public String[] imageTypeOptions;
    
    public int imageSize;
    public int colorFilter;
    public int imageType;
    public String siteFilter;
    public int pageSize;
    public String searchQuery;
    
    // array sources
    public final int imageSizeArray = R.array.image_size_array;
    public final int colorFilterArray = R.array.color_filter_array;
    public final int imageTypeArray = R.array.image_type_array;
    
    public ImageFilter(Activity parent){
        // Prepare default value
        imageSize = 0;
        colorFilter = 0;
        imageType = 0;
        siteFilter = "";
        pageSize = 8;

        this.imageSizeOptions = parent.getResources().getStringArray(this.imageSizeArray);
        this.imageTypeOptions = parent.getResources().getStringArray(this.imageTypeArray);
        this.colorFilterOptions= parent.getResources().getStringArray(this.colorFilterArray);

    }
    
    public String getImageSize(){
        if (imageSize <= imageSizeOptions.length){
            return imageSizeOptions[imageSize];
        } else return null;
    }
    public String getColorFilter(){
        if (colorFilter <= colorFilterOptions.length){
            return colorFilterOptions[colorFilter];
        } else return null;
    }
    public String getImageType(){
        if (imageType <= imageTypeOptions.length){
            return imageTypeOptions[imageType];
        } else return null;
    }
    public int getStartPage(int page){
        return ((page - 1) * pageSize);
        
    }
}
