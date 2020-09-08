package dominando.android.ex21_http;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Exemplo livro Dominando Android
 *
 * Criado em: 22/10/2015
 *
 * Ultima modificação: 11/11/2015
 */
public class MainActivity extends AppCompatActivity {

    LivroPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPagerAdapter = new LivroPagerAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(mPagerAdapter);
    }

    public class LivroPagerAdapter extends FragmentPagerAdapter {

        LivrosListFragment mList;
        LivrosGridFragment mGrid;

        public LivroPagerAdapter(FragmentManager fm) {
            super(fm);
            mList = new LivrosListFragment();
            mGrid = new LivrosGridFragment();
        }

        @Override
        public Fragment getItem(int position) {
            return (position == 0) ? mList : mGrid;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return (position == 0) ? "Lista" : "Grid";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
