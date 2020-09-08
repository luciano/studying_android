package dominando.android.testeshtml;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private Page page;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            page = intent.getParcelableExtra("page");
            webView = (WebView) findViewById(R.id.webView);

            webView.loadUrl(page.getUrl());
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
                }
            });

            ActionBar actionBar = getSupportActionBar();
            // setar titulo
            if ( actionBar != null) {
                //actionBar.setTitle("");
            }

        }
    }
}
