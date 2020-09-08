package dominando.android.testes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import dominando.android.testes.TextView.MainTextViewActivity;
import dominando.android.testes.activity.CicloVida;
import dominando.android.testes.activity.PrincipalActivity;
import dominando.android.testes.fragment.MainFragmentActivity;

/**
 *
 * Projeto criado para conter exemplos próprios e testes dos recursos
 * e ferramentas aprendidos.
 *
 * Criado em: 10/08/2015
 *
 * Ultima modificaçao: 07/10/2015
 */

public class MainActivity extends CicloVida implements View.OnClickListener {

    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnActivity = (Button) findViewById(R.id.button_activity);
        btnActivity.setOnClickListener(this);

        Button btnTextView = (Button) findViewById(R.id.button_text_view);
        btnTextView.setOnClickListener(onClickButtonTextViewListener());
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

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, PrincipalActivity.class));
    }

    private View.OnClickListener onClickButtonTextViewListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainTextViewActivity.class));
            }
        };
    }

    public void chamaMainFragment(View view) {
        startActivity(new Intent(this, MainFragmentActivity.class));
    }
}
