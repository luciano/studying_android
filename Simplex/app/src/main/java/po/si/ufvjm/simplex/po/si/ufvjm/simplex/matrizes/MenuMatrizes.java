package po.si.ufvjm.simplex.po.si.ufvjm.simplex.matrizes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import po.si.ufvjm.simplex.R;

public class MenuMatrizes extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_matrizes);
    }

    public void operacaoLinhas(View v) {
        startActivity(new Intent(this, Resultado.class));
    }
}
