package br.com.liugsilva.semfogo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import br.com.liugsilva.semfogo.R;

/**
 * Base para as proximas activities
 *
 * Created by Luciano on 19/12/2015.
 */
public class BaseActivity extends AppCompatActivity {

    public static final String TAGLOG = "LogLS";
    private boolean logs = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate: Bundle=" + savedInstanceState);


    }

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log("onSaveInstanceState: Bundle=" + outState);
    }

    public void setLog(boolean logs) {
        this.logs = logs;
    }

    private void log(String message) {
        if (logs) {
            Log.d(TAGLOG, getNameClass() + "." + message);
        }
    }

    private String getNameClass() {
        return getClass().toString().substring(getClass().toString().lastIndexOf("."));
    }
}
