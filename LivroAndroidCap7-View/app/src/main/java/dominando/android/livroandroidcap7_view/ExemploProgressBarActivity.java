package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ExemploProgressBarActivity extends Activity {

    private ProgressBar progressBar, progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_progress_bar);
        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; ++i) {
                            final int progress = i;

                            // atualiza a barra de progresso
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("LSLog", ">> progress: " + progress);
                                    progressBar.setProgress(progress);
                                    progressBar2.setProgress(progress);
                                }
                            });

                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e ) {}
                        }
                        Log.i("LSLog", "Fim");
                    }
                }.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exemplo_progress_bar, menu);
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
