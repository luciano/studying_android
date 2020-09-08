package dominando.android.ex17_navegacao;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class TelaPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private AbasPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pager);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        pagerAdapter = new AbasPagerAdapter(this, getSupportFragmentManager());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);
        
    }
}
