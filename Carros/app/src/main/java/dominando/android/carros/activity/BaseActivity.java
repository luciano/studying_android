package dominando.android.carros.activity;

import android.support.v7.widget.Toolbar;

import dominando.android.carros.R;

/**
 * Created by Luciano on 02/10/2015.
 */
public class BaseActivity extends livroandroid.lib.activity.BaseActivity {

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

    }

}
