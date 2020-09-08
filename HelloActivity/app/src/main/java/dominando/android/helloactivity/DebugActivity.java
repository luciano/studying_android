package dominando.android.helloactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Gera Logs do ciclo de vida
 */
public class DebugActivity extends AppCompatActivity {

    private final String TAG = "LogLS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClassName() + ".onCreate() chamado: " + savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, getClassName() + ".onDestroy() chamado.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, getClassName() + ".onStop() chamado.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, getClassName() + ".onPause() chamado.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, getClassName() + ".onStart() chamado.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, getClassName() + ".onResume() chamado.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, getClassName() + ".onRestart() chamado.");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, getClassName() + ".onSaveInstanceState() chamado.");
    }

    private String getClassName() {
        String name = getClass().getName();
        return name.substring(name.lastIndexOf("."));
    }
}
