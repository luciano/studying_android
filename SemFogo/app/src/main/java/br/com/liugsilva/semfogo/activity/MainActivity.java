package br.com.liugsilva.semfogo.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;

import br.com.liugsilva.semfogo.Debug;
import br.com.liugsilva.semfogo.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("cadastro", Context.MODE_PRIVATE);

        String nome = sharedPreferences.getString("nome", null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                criarDiretorios();
            }
        }).start();

        // não tem nenhum login
        if (nome == null) {
            setContentView(R.layout.activity_main);
        } else {
            // vai para menu
            setContentView(R.layout.activity_menu);
        }
    }

    private void criarDiretorios() {
        File picsDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM);
        File directory = new File(picsDir + "/SemFogo/");

        if (!directory.exists()) {
            boolean criouFotos = directory.mkdir();
            Debug.log("MainActivity.criarDiretorios(): DCIM/SemFogo = " + criouFotos);
        }

        File file = new File(directory + "/Profile/");
        if (!file.exists()) {
            boolean criouPerfil = file.mkdir();
            Debug.log("MainActivity.criarDiretorios(): DCIM/SemFogo = " + criouPerfil);
        }
    }
}
