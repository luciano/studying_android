package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class ExemploGridViewActivity extends Activity {

    int[] imagens = {
            R.drawable.triste,
            R.drawable.louco,
            R.drawable.android,
            R.drawable.feliz,
            R.drawable.triste,
            R.drawable.louco,
            R.drawable.android,
            R.drawable.feliz,
            R.drawable.triste,
            R.drawable.louco,
            R.drawable.android,
            R.drawable.feliz,
            R.drawable.triste,
            R.drawable.louco,
            R.drawable.android,
            R.drawable.feliz,
            R.drawable.triste,
            R.drawable.louco,
            R.drawable.android,
            R.drawable.feliz
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_grid_view);
        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImagemAdapter(this, imagens));
        gridView.setOnItemClickListener(onGridViewItemClick());
    }

    private AdapterView.OnItemClickListener onGridViewItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ExemploGridViewActivity.this, "Imagem selecionada: " + position, Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_grid_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
