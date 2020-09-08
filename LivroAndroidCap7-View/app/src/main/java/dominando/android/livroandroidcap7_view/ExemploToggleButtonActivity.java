package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ExemploToggleButtonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_toggle_button);

        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggle);

        final Button b = (Button) findViewById(R.id.ok);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean seleciodado = toggleButton.isChecked();
                Toast.makeText(ExemploToggleButtonActivity.this, "Selecionado: " + ((seleciodado)? "Ligado":"Desligado"), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_toggle_button, menu);
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
