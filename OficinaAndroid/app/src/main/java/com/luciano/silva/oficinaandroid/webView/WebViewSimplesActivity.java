package com.luciano.silva.oficinaandroid.webView;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.luciano.silva.oficinaandroid.R;

/**
 * Exemplo de uso de WebView para carregar uma pagina simples
 *
 * Criado em: 13/10/2015
 *
 * Ultima modificação: 13/10/2015
 */

public class WebViewSimplesActivity extends AppCompatActivity {

    private final static String URL = "http://android-developers.blogspot.com.br/2015/05/android-design-support-library.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_simples);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);

        final WebView webView = (WebView) findViewById(R.id.webView);

        progressBar.setVisibility(View.VISIBLE);

        webView.loadUrl(URL);

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
                Toast.makeText(WebViewSimplesActivity.this, "Erro ao carregar pagina.", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(WebViewSimplesActivity.this, "Redirecionando pagina!", Toast.LENGTH_SHORT).show();
                view.loadUrl(url);
                return false;
            }

        });

    }
}
