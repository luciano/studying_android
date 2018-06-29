package course.edx.luciano.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Activity1 extends AppCompatActivity {

    final String TAG1 = "Activity 1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Log.d(TAG1, "STATUS: onCreate");
    }

    public void goToActivity2(View view) {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG1, "STATUS: onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG1, "STATUS: onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG1, "STATUS: onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG1, "STATUS: onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG1, "STATUS: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG1, "STATUS: onDestroy");
    }
}
