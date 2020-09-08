package dominando.android.fragments_tabs_viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Luciano on 18/09/2015.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    public TabsAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int i) {

        if (i == 0) {
            return new Fragment1();
        } else if (i == 1) {
            return new Fragment2();
        }
        return new Fragment3();
    }
}
