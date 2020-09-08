package dominando.android.helloactionbar;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.widget.Toast;

/**
 *
 */
public class MyTabListener implements android.support.v7.app.ActionBar.TabListener {

    int tabIdx;
    Context context;

    public MyTabListener(Context ctx, int index) {
        context = ctx;
        tabIdx = index;
    }

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        // chamado quando seleciona uma tab
        Toast.makeText(context, "Selecionou a tab: " + tabIdx, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        // chamado quando a tab perde o foco (se outra tab é selecionada)
    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        // chamado quando uma tab e selecionada novamente
    }
}
