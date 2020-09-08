package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ExemploListViewCostumazadoActivity extends Activity implements AdapterView.OnItemClickListener {

    ListView listView;
    private List<Planeta> planetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_list_view);
        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        planetas = Planeta.getPlanetas();

        // listView
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new PlanetaAdapter(this, planetas));
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_list_view, menu);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Planeta p = this.planetas.get(position);
        Toast.makeText(ExemploListViewCostumazadoActivity.this, "Planeta: " + p.nome +
                ", position: " + position, Toast.LENGTH_SHORT).show();
    }
}
