package dominando.android.ex05_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;


public class AcaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao);

        TextView text = (TextView) findViewById(R.id.textAcao);

        Intent it = getIntent();

        if (it.getAction().equals(Intent.ACTION_VIEW)) {

            Uri uri = it.getData();

            text.setText("Ação customizada 2. "   +
                         "\nUri: " + uri.toString() +
                         "\nHost: " + uri.getHost() +
                         "\nPath:" + uri.getPath()   );

        } else if (it.getAction().equals("dominando.android.ACAO_CUSTOMIZADA")) {
            text.setText("Ação customizada 1");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_acao, menu);
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
