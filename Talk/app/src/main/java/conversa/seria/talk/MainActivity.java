package conversa.seria.talk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    static final String CATEGORIA = "TalkLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btIniciante = (Button) findViewById(R.id.btIniciante);
        final Button btIntermediario = (Button) findViewById(R.id.btIntermediario);
        final Button btAvancado = (Button) findViewById(R.id.btAvancado);

        btIniciante.setOnClickListener(this);
        btIntermediario.setOnClickListener(this);
        btAvancado.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btIniciante:
                startActivity(new Intent(this, InicianteActivity.class));
                break;
            case R.id.btIntermediario:
                startActivity(new Intent(this, IntermediarioActivity.class));
                break;
            case R.id.btAvancado:
                startActivity(new Intent(this, AvancadoActivity.class));
                break;
            default:
                Toast.makeText(this, "Opção Invalida!", Toast.LENGTH_LONG).show();
        }

    }

}
