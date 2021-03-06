package vn.edu.tdtu.lab07.exercise01;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import vn.edu.tdtu.lab07.R;

public class Exercise01Activity extends AppCompatActivity {

  private static final String APP_STARTUP_TIME_KEY = "APP_STARTUP_TIME";
  private TextView tvOpenTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.exercise01_activity);

    tvOpenTime = findViewById(R.id.tvOpenTimes);

    // Use SharedPreferences container to store primary value
    // the path to a preference file is: /data/data/packageName/shared_prefs/filename.
    SharedPreferences myPref = getSharedPreferences("lab07_exercise01_preference2",
        Activity.MODE_PRIVATE);

    // retrieving data from SharedPreferences container (apply default if needed)
    int appStartupTimes = myPref.getInt(APP_STARTUP_TIME_KEY, 0);

    // increase app startup time and save to SharedPreferences container
    // we need a Editor object to make preference changes
    int increasedAppStartupTime = appStartupTimes + 1;
    SharedPreferences.Editor editor = myPref.edit();
    editor.putInt(APP_STARTUP_TIME_KEY, increasedAppStartupTime);
    editor.apply();

    tvOpenTime.setText(String.valueOf(increasedAppStartupTime));
  }
}
