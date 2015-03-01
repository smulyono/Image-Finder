package courses.smulyono.me.imagefinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import courses.smulyono.me.imagefinder.R;
import courses.smulyono.me.imagefinder.models.ImageResult;
import courses.smulyono.me.imagefinder.util.DeviceDimensionsHelper;

/**
 * Created by smulyono on 2/28/15.
 */
public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {
    
    private class ViewHolder{
        public ImageView ivImage;
    }
    
    public ImageResultsAdapter(Context context,List<ImageResult> images) {
        super(context, android.R.layout.simple_list_item_1, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageResult imageInfo = getItem(position);
        
        final ViewHolder viewHolder;
        
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent,false);
            
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        // load remote image
        int currentWidth = DeviceDimensionsHelper.getDisplayWidth(viewHolder.ivImage.getContext());
        viewHolder.ivImage.setImageResource(0);
        // check on the ratio of tbWidth / tbHeight

        Picasso.with(getContext()).load(imageInfo.thumbUrl)
                .placeholder(R.mipmap.ic_isearch)
                .resize(currentWidth, 0)
                .into(viewHolder.ivImage);
        
        return convertView;
    }
}
