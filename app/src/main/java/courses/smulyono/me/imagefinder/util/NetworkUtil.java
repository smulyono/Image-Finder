package courses.smulyono.me.imagefinder.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by smulyono on 3/1/15.
 */
public class NetworkUtil {
    
    public static Boolean isNetworkAvailable(Activity parent) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) parent.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
