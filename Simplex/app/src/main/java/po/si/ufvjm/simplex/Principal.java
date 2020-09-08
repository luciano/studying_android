package po.si.ufvjm.simplex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import po.si.ufvjm.simplex.po.si.ufvjm.simplex.matrizes.MenuMatrizes;

public class Principal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void chamaIserirDados(View v) {
        startActivity(new Intent(this, InserirDados.class));
    }

    public void operacaoMatriz(View v) {

        startActivity(new Intent(this, MenuMatrizes.class));
    }

    public void chamaInserirQuadro(View v) {
        startActivity(new Intent(this, InserirQuadro.class));
    }

    public void chamaExemplo(View v) {
        startActivity(new Intent(this, PreDefinido.class));
    }
}
