package ca.seemahejazi.photosecurity.project2v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import ca.seemahejazi.photosecurity.project2v2.FirebaseCloudMessaging.DeviceSharedPref;
import ca.seemahejazi.photosecurity.project2v2.FirebaseCloudMessaging.FirebaseInstanceIDService;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity {
    DeviceSharedPref mDeviceSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void loginClick(View view) {
        mDeviceSharedPref = new DeviceSharedPref(this);
        // Set up the login form.

        Intent openNewWindow = new Intent(this, ViewActivity.class);
        EditText mEmailView = (EditText) findViewById(R.id.editEmail);
        String email = mEmailView.getText().toString();

        mDeviceSharedPref.setUserName(email);

        if (TextUtils.isEmpty(mDeviceSharedPref.getDeviceId())) {
            Intent startDevice = new Intent(MainActivity.this, FirebaseInstanceIDService.class);
            startService(startDevice);
        }
        finish();
        startActivity(openNewWindow);

    }
}