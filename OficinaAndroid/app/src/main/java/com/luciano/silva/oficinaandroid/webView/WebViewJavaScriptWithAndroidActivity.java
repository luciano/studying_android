package com.luciano.silva.oficinaandroid.webView;

import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.luciano.silva.oficinaandroid.R;

public class WebViewJavaScriptWithAndroidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_java_script_with_android);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);

        final WebView webView = (WebView) findViewById(R.id.webView);

        progressBar.setVisibility(View.VISIBLE);

        webView.loadUrl("file:///android_asset/webViewHTML/meu.html");

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            // Pagina comecou a carregar
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            // Erro ao carregar pagina
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(WebViewJavaScriptWithAndroidActivity.this, "Erro ao carregar pagina.", Toast.LENGTH_SHORT).show();
            }

            // Pagina terminou de carregar
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            // Faz com que quando pagina for redirecionada ela carregue no proprio WebView
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

        });

        webView.addJavascriptInterface(new ClasseAndroidInterface(), "Android");
    }

    class ClasseAndroidInterface {
        @JavascriptInterface
        public void funcaoAndroid() {
            Toast.makeText(WebViewJavaScriptWithAndroidActivity.this, "Função no codigo Android" +
                    " chamada apartir do JavaScript no HTML da pagina.", Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void mostraNome(String nome) {
            Toast.makeText(WebViewJavaScriptWithAndroidActivity.this, "Parametro: " + nome, Toast.LENGTH_SHORT).show();
        }
    }
}
