package com.luciano.silva.oficinaandroid.webView;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.luciano.silva.oficinaandroid.R;

/**
 * Exemplo de uso de WebView para carregar uma pagina simples com SwipeRefresh para
 * atualizar a pagina com o movimento de swipe vertical, puxar tela pra baixo faz com
 * que a seja recarregada a pagina
 *
 * Criado em: 13/10/2015
 *
 * Ultima modificação: 13/10/2015
 */

public class WebViewSwipeRefreshActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_swipe_refresh);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_color1, R.color.refresh_color3,
                                 R.color.refresh_color2, R.color.refresh_color4, R.color.refresh_color5);

        mWebView = (WebView) findViewById(R.id.webView);

        mWebView.loadUrl("http://www.google.com");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.reload();
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // faz ir e voltar no historico de navegação
        switch (item.getItemId()) {

            case R.id.go_forward:
                if (mWebView.canGoForward())
                    mWebView.goForward();
                return true;

            case R.id.go_back:
                if (mWebView.canGoBack())
                    mWebView.goBack();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
