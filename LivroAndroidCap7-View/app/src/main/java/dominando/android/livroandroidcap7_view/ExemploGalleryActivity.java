package dominando.android.livroandroidcap7_view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExemploGalleryActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_exemplo_gallery);

        Gallery g = (Gallery) findViewById(R.id.gallery);
        g.setAdapter(new ImagemAdapter(this, imagens));
        g.setOnItemClickListener(onGalleryItemClick(this));
    }

    private AdapterView.OnItemClickListener onGalleryItemClick(final Context context) {

        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View view1 = LayoutInflater.from(context).inflate(R.layout.toast_view, null);

                TextView nome = (TextView) view1.findViewById(R.id.textView4);
                nome.setText(nomes[position]);
                ImageView img = (ImageView)view1.findViewById(R.id.imageView);
                img.setImageResource(imagens[position]);

                Toast t = new Toast(context);
                t.setView(view1);
                t.show();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_gallery, menu);
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
