package dominando.android.facebookapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Usei keytool do endereco onde esta o jdk
 * e o openssl do endereco do git
 * para funcionar o comando do keytool
 *
 * A integração nao funciona do jeito que o Thiengo ensina
 * nao existe mais as classes que ele usa
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // "Integração Facebook",
        String[] opcoes = {"LoginButton", "GraphAPI", "LoginManager"};

        ListView mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes));
        mListView.setOnItemClickListener(onClickLista());
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private AdapterView.OnItemClickListener onClickLista() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, LoginButtonFacebookActivity.class));
                        //startActivity(new Intent(MainActivity.this, IntegracaoFacebookActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, GraphAPIActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, LoginManagerFacebookActivity.class));
                        break;
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
