package dominando.android.fragments_actionbartabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

/**
 * Created by Luciano on 18/09/2015.
 */
public class MyTabListener implements ActionBar.TabListener {

    private Fragment fragment;
    private Context context;

    public MyTabListener(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // trocar o fragment dinamicamente ao clicar
        fragmentTransaction.replace(R.id.layoutFragment, this.fragment, null);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
