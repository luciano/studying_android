package po.si.ufvjm.simplex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InserirDados extends Activity {

    //problema sapatos e cintos
//    private Double[][] matriz = new Double[][] {
//            {0.0,  1.0,  2.0, 3.0, 4.0,  0.0},
//            {3.0, 10.0, 12.0, 1.0, 0.0, 60.0},
//            {4.0,  2.0,  1.0, 0.0, 1.0,  6.0},
//            {0.0,  5.0,  2.0, 0.0, 0.0,  0.0}
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_dados);

        final EditText funcaoObjetivo = (EditText) findViewById(R.id.func_obj);
        final EditText restricoes = (EditText) findViewById(R.id.restricoes);
        final Button buttonOk = (Button) findViewById(R.id.button_ok);

        Log.i("Depuracao", "Iniciou onCreate.");

        buttonOk.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(InserirDados.this, "Em construção :)", Toast.LENGTH_LONG).show();
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
