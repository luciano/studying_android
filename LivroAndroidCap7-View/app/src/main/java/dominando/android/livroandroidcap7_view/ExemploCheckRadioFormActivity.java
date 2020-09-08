package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ExemploCheckRadioFormActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_check_radio_form);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final EditText textNome = (EditText) findViewById(R.id.textNome);
        final RadioGroup group = (RadioGroup) findViewById(R.id.group1);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean sim = checkedId == R.id.radioSim;
                boolean nao = checkedId == R.id.radioNao;

                if (sim) {
                    Log.i("LSLog", "Marcou radio Sim: " + checkedId);
                } else if (nao) {
                    Log.i("LSLog", "Marcou radio Não: " + checkedId);
                }
            }
        });

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkReceberEmail);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("LSLog", "check: " + isChecked);
            }
        });

        Button b = (Button) findViewById(R.id.buttonEnviar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean concorda = R.id.radioSim == group.getCheckedRadioButtonId();
                boolean receberEmail = checkBox.isChecked();
                StringBuffer sb = new StringBuffer();
                sb.append("Nome: ").append(textNome.getText())
                        .append("\nConcorda: ").append(concorda ? "Sim": "Não")
                        .append("\nReceber Email: ").append(receberEmail ? "Sim": "Não");
                Toast.makeText(ExemploCheckRadioFormActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_check_radio_form, menu);
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
