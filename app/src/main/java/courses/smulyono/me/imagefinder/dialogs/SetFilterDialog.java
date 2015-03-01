package courses.smulyono.me.imagefinder.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import courses.smulyono.me.imagefinder.R;
import courses.smulyono.me.imagefinder.activities.SearchActivity;

/**
 * Created by smulyono on 2/28/15.
 */
public class SetFilterDialog extends DialogFragment {
    
    private Spinner spImageSize;
    private Spinner spColorFilter;
    private Spinner spImageType;
    private EditText etSiteFilter;
    
    private SearchActivity parentActivity;
    
    public static SetFilterDialog newInstance(){
        SetFilterDialog newDialog = new SetFilterDialog();
        return newDialog;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        parentActivity = (SearchActivity) activity;
        
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.filter_dialog, null);
    
        // image size
        spImageSize = (Spinner) view.findViewById(R.id.spImageSize);
        ArrayAdapter<CharSequence> imageSizeadapter = ArrayAdapter.createFromResource(getActivity(), parentActivity.imageFilter.imageSizeArray, android.R.layout.simple_spinner_dropdown_item);
        spImageSize.setAdapter(imageSizeadapter);
        spImageSize.setSelection(parentActivity.imageFilter.imageSize);
        
        // color filter
        spColorFilter = (Spinner) view.findViewById(R.id.spColorFilter);
        ArrayAdapter<CharSequence> colorFilterAdapter = ArrayAdapter.createFromResource(getActivity(), parentActivity.imageFilter.colorFilterArray, android.R.layout.simple_spinner_dropdown_item);
        spColorFilter.setAdapter(colorFilterAdapter);
        spColorFilter.setSelection(parentActivity.imageFilter.colorFilter);
        
        // image type
        spImageType = (Spinner) view.findViewById(R.id.spImageType);
        ArrayAdapter<CharSequence> imageTypeAdapter = ArrayAdapter.createFromResource(getActivity(), parentActivity.imageFilter.imageTypeArray, android.R.layout.simple_spinner_dropdown_item);
        spImageType.setAdapter(imageTypeAdapter);
        spImageType.setSelection(parentActivity.imageFilter.imageType);
        
        // site filter
        etSiteFilter = (EditText) view.findViewById(R.id.etSiteFilter);
        etSiteFilter.setText(parentActivity.imageFilter.siteFilter);
        
        builder.setView(view)
            .setTitle(R.string.filter_title)
            .setPositiveButton(R.string.save_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // save the filter
                    parentActivity.imageFilter.imageType = spImageType.getSelectedItemPosition();
                    parentActivity.imageFilter.imageSize = spImageSize.getSelectedItemPosition();
                    parentActivity.imageFilter.colorFilter = spColorFilter.getSelectedItemPosition();
                    parentActivity.imageFilter.siteFilter = etSiteFilter.getText().toString();
                    Log.d(SearchActivity.APP_TAG, "saved!");
                }
            })
            .setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SetFilterDialog.this.getDialog().cancel();
                }
            });
        
        return builder.create();
    }

}
