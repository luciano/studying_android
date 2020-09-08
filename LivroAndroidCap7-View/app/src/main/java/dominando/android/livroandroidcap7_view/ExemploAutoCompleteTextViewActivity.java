package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class ExemploAutoCompleteTextViewActivity extends Activity {

    private static final String[] ESTADOS = new String[] {
            "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceára", "Minas Gerais",
            "São Paulo", "Santa Catarina", "Sergipe", "Tocantins"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_auto_complete_text_view);

        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.estados);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ESTADOS);
        autoCompleteTextView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_auto_complete_text_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
