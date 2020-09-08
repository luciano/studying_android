package dominando.android.hellohandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DemoHandlerPostActivity extends AppCompatActivity {
    private static final int MENSAGEM_TESTE = 1;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_handler_message);

        findViewById(R.id.btEnviar).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cria mensagem com delay de 3 segundos
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "A mensagem chegou com Runnable!", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo_handler_post, menu);
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
