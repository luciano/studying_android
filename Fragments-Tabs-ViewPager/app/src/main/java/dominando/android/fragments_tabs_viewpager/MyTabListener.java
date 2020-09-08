package dominando.android.fragments_tabs_viewpager;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

/**
 * Created by Luciano on 18/09/2015.
 */
public class MyTabListener implements ActionBar.TabListener {

    private ViewPager mViewPager;
    private int idx;

    public MyTabListener(ViewPager mViewPager, int i) {
        this.mViewPager = mViewPager;
        idx = i;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // navega para a pagina desejada do ViewPager
        mViewPager.setCurrentItem(idx);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
