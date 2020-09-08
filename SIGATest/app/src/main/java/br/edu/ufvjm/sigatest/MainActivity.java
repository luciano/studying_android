package br.edu.ufvjm.sigatest;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {


    boolean notas = false;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.tv);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        //progressBar.setVisibility(View.VISIBLE);

        final WebView webView = (WebView) findViewById(R.id.net);

        webView.setVisibility(View.INVISIBLE);
        webView.getSettings().setSupportZoom(true);

        final EditText et = (EditText) findViewById(R.id.ed);

        final Button b = (Button) findViewById(R.id.bt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et.getText().toString().isEmpty()) {

                    String s = "http://" + et.getText().toString();

                    webView.loadUrl(s);
                    webView.setVisibility(View.VISIBLE);

                } else {
                    //webView.loadUrl("http://www.jw.org");
                    notas = true;
                    webView.setVisibility(View.VISIBLE);
                    webView.loadUrl("http://siga.ufvjm.edu.br/?uid=20131016014&pwd=liuciano&btnLogin=Entrar&tries=&return_to=index.php%3F&challenge=198150324855c15667d55af&response=&frm55c15667d510f_action=http%3A%2F%2Fsiga.ufvjm.edu.br%2Findex.php%3Fmodule%3Dcommon%26action%3Dmain&__VIEWSTATE=&__ISPOSTBACK=yes&__EVENTTARGETVALUE=btnLogin%3Aclick&__EVENTARGUMENT=&__FORMSUBMIT=frm55c15667d510f");
                    //http%3A%2F%2Fsiga.ufvjm.edu.br%2Findex.php%3Fmodule%3Dcommon%26action%3Dmain%3Aaluno%3Aquadronotasparciais
                    //webView.loadUrl("http://siga.ufvjm.edu.br/?uid=20131016014&pwd=liuciano&btnLogin=Entrar&tries=&return_to=index.php%3F&challenge=198150324855c15667d55af&response=&frm55c15667d510f_action=http%3A%2F%2Fsiga.ufvjm.edu.br%2Findex.php%3Fmodule%3Dcommon%26action%3Dmain%3Aaluno%3Aquadronotasparciais&__VIEWSTATE=&__ISPOSTBACK=yes&__EVENTTARGETVALUE=btnLogin%3Aclick&__EVENTARGUMENT=&__FORMSUBMIT=frm55c15667d510f");
                }
            }
        });


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                tv.setText(view.getUrl());
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                tv.setText(view.getUrl());
                progressBar.setVisibility(View.GONE);
                //String UR = verifica(view.getUrl());
                final String n = view.getUrl();
                if (notas) {
                    view.loadUrl("http://siga.ufvjm.edu.br/index.php?module=ensino&action=main:aluno:quadronotasparciais");

                    view.saveWebArchive("teste.txt");

                    notas = false;
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                URL link = new URL(n);
                                URLConnection connection = link.openConnection();


                                Log.d("LogLS", "Link path: " + link.getPath());
                                Log.d("LogLS", "Link string: " + link.toString());
                                Log.d("LogLS", "Link user: " + link.getUserInfo());
                                Log.d("LogLS", "Link port: " + link.getPort());

                                try {
                                    InputStreamReader r = new InputStreamReader(link.openStream());
                                    Log.d("LogLS", "Ready file: " + r.ready());

                                    char[] b = new char[1024];
                                    while (r.read(b) > -1) {
                                        Log.d("LogLS", new String(b));
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }

        });

        //webView.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private String verifica(String url) {
        if("http://siga.ufvjm.edu.br/index.php?module=common&action=main".equals(url)
                || "http://siga.ufvjm.edu.br/index.php?module=common&action=main:RecebimentoNoticias".equals(url)
                || "http://siga.ufvjm.edu.br/index.php?module=common&action=main:main/".equals(url)) {

            return "http://siga.ufvjm.edu.br/index.php?module=ensino&action=main:aluno:quadronotasparciais";
        }
        return url;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == android.R.id.home) {
            Toast.makeText(this, "Up navegation", Toast.LENGTH_LONG).show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}