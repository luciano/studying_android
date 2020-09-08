package dominando.android.ex14_webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
/**
 * Exemplo livro Dominando Android
 *
 * Criado em: 27/08/2015
 *
 * Ultima modificação: 27/08/2015
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://nglauber.blogspot.com");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // comecou a carregar
                Toast.makeText(MainActivity.this, "Pagina começou a carregar.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // terminou de carregar
                Toast.makeText(MainActivity.this, "Pagina carregada.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
