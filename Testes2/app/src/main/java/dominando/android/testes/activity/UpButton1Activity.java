package dominando.android.testes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import dominando.android.testes.R;

/**
 * Logs dos principais ciclos de vida da Activity.
 * Ao Precionar o Up Button ele alem de destruir a atual activity destroi as demais
 * na hierarquia.
 *
 * Home -> Activity1
 *
 * Ao pressionar Up Button na Activity1, metodo onPause é chamado.
 * Home é destruida com onDestroy e entao recriada com onCreate
 * Activity1 é destruida com onDestroy.
 *
 * Com outra activity na hierarquia todas sao destruidas.
 *
 * Home -> Activity1 -> Activity2
 *
 * Ao pressionar Up Button na Activity2, metodo onPause é chamado.
 * Activity1 é destruida com onDestroy.
 * Home é destruida com onDestroy e entao recriada com onCreate
 * Activity2 é destruida com onDestroy.
 *
 * Criado em: 10/08/2015
 *
 * Ultima modificação: 10/08/2015
 */

public class UpButton1Activity extends CicloVida implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_button1);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_up_button1, menu);
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

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, UpButton2Activity.class));
    }
}
