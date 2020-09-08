package dominando.android.testes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Pega os logs do ciclo de vida da aplicação
 *
 * Criado em: 10/08/2015
 *
 * Ultima modificaçao: 10/08/2015
 */
public class CicloVida extends AppCompatActivity {

    private final String CATEGORIA = "LogLS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(CATEGORIA, getNameClass() + ":: onCreate(Bundle), Bundle: " + savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(CATEGORIA, getNameClass() + ":: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(CATEGORIA, getNameClass() + ":: onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(CATEGORIA, getNameClass() + ":: onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(CATEGORIA, getNameClass() + ":: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(CATEGORIA, getNameClass() + ":: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(CATEGORIA, getNameClass() + ":: onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(CATEGORIA, getNameClass() + ":: onSaveInstanceState(Bundle), Bundle: " + outState);
    }


    public String getNameClass() {
        String name = getClass().getName();
        name = name.substring(name.lastIndexOf(".") + 1);
        return name;
    }
}
