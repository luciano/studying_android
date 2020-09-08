package dominando.android.fragments_actionbartabs;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Exemplo livro Google Android
 *
 * Criado em: 18/09/2015
 *
 * Ultima modificação: 18/09/2015
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Tab 1
        ActionBar.Tab tab1 = actionBar.newTab().setText("Frag 1");
        tab1.setTabListener(new MyTabListener(this, new Fragment1()));
        actionBar.addTab(tab1);

        // Tab 2
        ActionBar.Tab tab2 = actionBar.newTab().setText("Frag 2");
        tab2.setTabListener(new MyTabListener(this, new Fragment2()));
        actionBar.addTab(tab2);

        // Tab 3
        ActionBar.Tab tab3 = actionBar.newTab().setText("Frag 3");
        tab3.setTabListener(new MyTabListener(this, new Fragment3()));
        actionBar.addTab(tab3);
    }
}
