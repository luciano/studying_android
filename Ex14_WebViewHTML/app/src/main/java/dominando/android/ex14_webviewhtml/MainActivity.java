package dominando.android.ex14_webviewhtml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
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

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "dominando");

        webView.loadUrl("file:///android_asset/meu.html");

    }

    @JavascriptInterface
    public void showToast(String s, String t) {
        Toast.makeText(this, "Nome: " + s + " Idade: " + t, Toast.LENGTH_LONG).show();

    }
}
