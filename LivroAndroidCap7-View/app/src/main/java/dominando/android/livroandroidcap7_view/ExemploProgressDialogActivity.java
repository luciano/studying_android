package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ExemploProgressDialogActivity extends Activity {

    private static final String link = "http://livroandroid.com.br/imgs/livro_android.png";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_progress_dialog);
        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        // abre janela com a barra de progresso
        progressDialog = ProgressDialog.show(this, "Exemplo", "Buscando imagem, por favor, aguarde...", false, true);

        downloadImagem(link);
    }

    // faz download em nova thread
    private void downloadImagem(final String urlImage) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlImage);
                    InputStream in = url.openStream();

                    // converte a InputStream do Java para Bitmap
                    final Bitmap imagem = BitmapFactory.decodeStream(in);
                    in.close();
                    // atualizar tela
                    atualizarImagem(imagem);
                } catch (IOException e) {
                    Log.i("LSLog", "Erro ao fazer o download");
                }
            }
        }.start();
    }

    private void atualizarImagem(final Bitmap imagem) {
        // codigo necessario pois foi aberta uma nova thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // fecha a janela de progresso
                progressDialog.dismiss();
                ImageView img = (ImageView) findViewById(R.id.imagem);
                img.setImageBitmap(imagem);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_progress_dialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
