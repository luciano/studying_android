package dominando.android.hellotoolbarstandalone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Exemplo livro Google Android
 *
 * Criado em: 14/09/2015
 *
 * Ultima modificação: 14/09/2015
 *
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_up);

        // trata evento de clique na Toolbar
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.action_settings) {
                    Toast.makeText(MainActivity.this, "Botão Settings", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (id == R.id.action_smile) {
                    Toast.makeText(MainActivity.this, "Botão Smile", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        // Up Navegation
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // infla os itens de menu na Toolbar
        toolbar.inflateMenu(R.menu.menu_main);
    }
}
