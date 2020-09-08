package dominando.android.planetas_fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PlanetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planeta);

        // parametro enviado pela intent da activity
        String planeta = getIntent().getStringExtra("planeta");

        if (savedInstanceState == null) {
            PlanetaFragment planetaFragment = new PlanetaFragment();
            planetaFragment.setArguments(getIntent().getExtras());

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.layoutFrag, planetaFragment, "PlanetaFragment");
            fragmentTransaction.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(planeta);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
