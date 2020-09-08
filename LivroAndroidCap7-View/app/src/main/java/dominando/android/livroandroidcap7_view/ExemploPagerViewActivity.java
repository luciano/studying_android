package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ExemploPagerViewActivity extends Activity {

    int[] imagens = {
            R.drawable.planeta_01_mercurio,
            R.drawable.planeta_02_venus,
            R.drawable.planeta_03_terra,
            R.drawable.planeta_04_marte,
            R.drawable.planeta_05_jupiter,
            R.drawable.planeta_06_saturno,
            R.drawable.planeta_07_urano,
            R.drawable.planeta_08_neptuno,
            R.drawable.planeta_09_plutao
    };

    String nomes[] = {
            "Mercurio", "Venus", "Terra", "Marte", "Jupter",
            "Saturno", "Urano", "Neturno", "Plutão"    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_pager_view);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new ImagemPagerAdapter(this, imagens));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(ExemploPagerViewActivity.this, "Imagem: " + nomes[position] + " - posicao: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_pager_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
