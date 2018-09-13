package ca.seemahejazi.photosecurity.project2v2.FirebaseCloudMessaging;

/**
 * Created by Seema on 2016-11-23.
 *
 */
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class DeviceSharedPref {
    private Context context;

    private static final String ACCOUNT = "account";
    private static final String DEVICE_KEY = "deviceId";
    private static final String LOGIN_KEY = "login_user";

    public DeviceSharedPref(Context context) {
        this.context = context;
    }

    public String getDeviceId(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp = context.getSharedPreferences(ACCOUNT, mode);
        return sp.getString(DEVICE_KEY, "");
    }
    public void setDeviceId(String s){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp =  context.getSharedPreferences(ACCOUNT, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(DEVICE_KEY, s);
        editor.commit();
    }
    public String getUserName(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp = context.getSharedPreferences(ACCOUNT, mode);
        return sp.getString(LOGIN_KEY, "");
    }
    public void setUserName(String s){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp =  context.getSharedPreferences(ACCOUNT, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(LOGIN_KEY, s);
        editor.commit();
    }
}