package startup.weekend.diamantina.helpprofissional;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DetalhesProfissionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_profissional);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        DetalhesProfissionalActivityFragment fragment = DetalhesProfissionalActivityFragment.newInstance(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:038 988-227-816")));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
