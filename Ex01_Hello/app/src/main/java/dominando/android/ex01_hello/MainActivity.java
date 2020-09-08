package dominando.android.ex01_hello;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

/**
 * Primeiro exemplo do livro Dominando Android
 *
 * Criando em: 10/08/2015
 *
 * Ultima odificaçao: 10/08/2015
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // modo de pegar uma string da pasta strings no codigo Java.
        getString(R.string.app_name);

        // É possivel descobrir as configurações de recurso do apararelho
        // por meio das classes android.content.res.Configuration ou da classe
        // android.util.DisplayMetrics.

        Configuration configuration = getResources().getConfiguration();
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        // 1 - Portrait - vertical
        // 2 - Landscape - horizontal
        int orientation = configuration.orientation;

        // proporcao de acordo com densidade da tela (DPI)
        float density = metrics.density; // (dpi / 160)

        // classificacao da tela
        // small, normal, large, xlarge
        float height = metrics.heightPixels / metrics.density; // pixeis / (dpi / 160 )
        float width = metrics.widthPixels / metrics.density;

        int mcc = configuration.mcc;
        int mnc = configuration.mnc;

        Locale locale = configuration.locale;

        Log.d("NGVL", "density: " + density);
        Log.d("NGVL", "orientation: " + orientation);
        Log.d("NGVL", "height: " + height);
        Log.d("NGVL", "width: " + width);
        Log.d("NGVL", "language: " + locale.getLanguage() + "-" + locale.getCountry());
        Log.d("NGVL", "mcc: " + mcc);
        Log.d("NGVL", "mnc: " + mnc);

        // extras
        int dpiM = metrics.densityDpi;
        int dpi = configuration.densityDpi;
        int heightDP = configuration.screenHeightDp;
        int widthDP = configuration.screenWidthDp;

        Log.d("NGVL", "[extra] densityDPI(metrics): " + dpiM);
        Log.d("NGVL", "[extra] densityDPI: " + dpi);
        Log.d("NGVL", "[extra] hegthDP: " + heightDP);
        Log.d("NGVL", "[extra] widthDP: " + widthDP);

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

        return super.onOptionsItemSelected(item);
    }
}
