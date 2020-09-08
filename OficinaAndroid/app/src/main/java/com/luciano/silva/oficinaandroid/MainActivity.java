package com.luciano.silva.oficinaandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Oficina Android
 *
 * Objetivo de servir como referencia para projetos futuros.
 * Vai incluir exemplos de como usar a maioria dos recursos
 * apresentados nos livros sobre android e uma breve explicação do
 * codigo feito para atingir o resultado final.
 *
 * Criado em: 08/10/2015
 *
 * Ultima modificação: 09/10/2015
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(px);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
            
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Sobre Oficina Android", Toast.LENGTH_SHORT).show();
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
