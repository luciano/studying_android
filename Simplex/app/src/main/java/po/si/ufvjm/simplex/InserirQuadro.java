package po.si.ufvjm.simplex;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class InserirQuadro extends Activity {

    private boolean duasFases = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_quadro);

        final EditText restricoes = (EditText) findViewById(R.id.restricoes);
        final Button buttonOk = (Button) findViewById(R.id.button_ok);
        final CheckBox ckDuasFases = (CheckBox) findViewById(R.id.checkBoxDF);

        if(ckDuasFases.isChecked()) {
            duasFases = true;
            Log.i("dep", "CheckBox isChecked");

        }

        Log.i("Depuracao", "Iniciou onCreate.");

        buttonOk.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(InserirQuadro.this, "Em construção :)", Toast.LENGTH_LONG).show();
                //Log.i("Depuracao", "Entrou no onCLickListener");

                //Simplex s = new Simplex();
                //s.Solution(matriz);
                //String result = s.mostrarResult();

                //Log.i("Depuracao", "String result depois Simplex: \n" + result);

                //Intent it = new Intent(InserirDados.this, Resultado.class);

                //it.putExtra("simplex", result);
                //startActivity(it);
            }
        });

    }
}
