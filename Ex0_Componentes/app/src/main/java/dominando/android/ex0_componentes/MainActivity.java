package dominando.android.ex0_componentes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CompoundButton checkbox, switchView;
    SeekBar seekBar;
    Spinner spinner;
    RadioGroup radioGroup;
    TextView txtValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkbox = (CompoundButton) findViewById(R.id.ckbHabilitar);
        switchView = (CompoundButton) findViewById(R.id.swhHabilitar);
        seekBar = (SeekBar) findViewById(R.id.skbValor);
        radioGroup = (RadioGroup) findViewById(R.id.rgOpcoes);
        txtValor = (TextView) findViewById(R.id.txtValor);
        spinner = (Spinner) findViewById(R.id.spnNomes);

        configurarSpinner();
        configurarSeekBar();

        // atribuindo programaticamente os valores padrao
        checkbox.setChecked(true);
        seekBar.setProgress(20);
        spinner.setSelection(2);
        radioGroup.check(R.id.rgOpcao2);

    }

    private void configurarSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtValor.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void configurarSpinner() {

        String[] nomes = new String[] {
            "Eric", "Diana", "Presto", "Hank", "Sheila", "Bob" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void verValores(View view) {
        int idRadioSelecionado = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(idRadioSelecionado);

        String habilitado = checkbox.isChecked() ? "Habilitado" : "Desabilitado";
        String habilitado1 = switchView.isChecked() ? "Habilitado" : "Desabilitado";
        String valor = "Valor: " + seekBar.getProgress();
        String nome = "Nome: " + spinner.getSelectedItem().toString();
        String opcao = "Opacao: " + radioButton.getText().toString();

        StringBuilder mensagem = new StringBuilder();
        mensagem.append(habilitado).append("\n")
                .append(habilitado1).append("\n")
                .append(valor).append("\n")
                .append(nome).append("\n")
                .append(opcao).append("\n");

        Toast.makeText(this, mensagem.toString(), Toast.LENGTH_SHORT).show();

    }
}
