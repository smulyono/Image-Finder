package courses.smulyono.me.imagefinder.models;

import java.io.Serializable;

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
    
    public ImageFilter(){
        // Prepare default value
        imageSize = 0;
        colorFilter = 0;
        imageType = 0;
        siteFilter = "";
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
}
