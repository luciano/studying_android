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


public class DemoHandlerMessageActivity extends AppCompatActivity {

    private static final int MENSAGEM_TESTE = 1;
    private Handler mHandler = new TesteHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_handler_message);

        findViewById(R.id.btEnviar).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cria mensagem com delay de 3 segundos
                Message message = new Message();
                message.what = MENSAGEM_TESTE;

                // enviar mensagem
                mHandler.sendMessageDelayed(message, 3000);
            }
        });

    }

    // utilizado para receber a mensagem interna
    private class TesteHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // atributo what permite identificar mensagem
            switch (msg.what) {
                case MENSAGEM_TESTE:
                    Toast.makeText(DemoHandlerMessageActivity.this, "A mensagem chegou!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
