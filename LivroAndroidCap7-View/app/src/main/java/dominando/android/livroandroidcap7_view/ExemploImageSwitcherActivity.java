package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ExemploImageSwitcherActivity extends Activity {

    private int[] imagens = {
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
    private int index = 0;

    private ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_image_switcher);

        // configura o ImageSwitcher e os efeitos
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {

                ImageView imageView = new ImageView(getBaseContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });


        imageSwitcher.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        View btProxima = findViewById(R.id.btProxima);
        btProxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == imagens.length) {index = 0;}

                imageSwitcher.setImageResource(imagens[index++]);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_image_switcher, menu);
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
