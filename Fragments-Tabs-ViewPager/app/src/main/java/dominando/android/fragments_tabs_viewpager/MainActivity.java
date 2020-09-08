package dominando.android.fragments_tabs_viewpager;

import android.support.v4.view.ViewPager;
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

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewPager
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));

        // configura Tabs
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText("Frag 1").setTabListener(new MyTabListener(mViewPager, 0)));
        actionBar.addTab(actionBar.newTab().setText("Frag 2").setTabListener(new MyTabListener(mViewPager, 1)));
        actionBar.addTab(actionBar.newTab().setText("Frag 3").setTabListener(new MyTabListener(mViewPager, 2)));

        // se o ViewPager trocar de pagina, atualiza a Tab

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                // se fizer swipe no ViewPager atualiza a tab
                actionBar.setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
