package dominando.android.hellofragments;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Exemplo livro Google Android
 *
 * Criado em: 18/09/2015
 *
 * Ultima modificação: 18/09/2015
 *
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adiciona fragment dinamicamente pela API
        if (savedInstanceState == null) {
        // evita colocar um fragment em cima de outro,
        // pois ele persiste quando muda de orientacao mas activity é recriada
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment1 fragment1 = new Fragment1();
            fragmentTransaction.add(R.id.layoutFrag, fragment1, "Fragment1");
            fragmentTransaction.commit();

        }

    }
}
