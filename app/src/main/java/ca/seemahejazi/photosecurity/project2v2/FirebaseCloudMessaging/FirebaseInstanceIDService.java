package ca.seemahejazi.photosecurity.project2v2.FirebaseCloudMessaging;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by keithweaver on 2016-11-21.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = FirebaseInstanceIDService.class.getSimpleName();

    DeviceSharedPref mDeviceSharedPref;

    @Override
    public void onTokenRefresh() {
        Log.v(TAG, "onTokenRefresh()");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        mDeviceSharedPref = new DeviceSharedPref(this);
        mDeviceSharedPref.setDeviceId(refreshedToken);
    }
}
