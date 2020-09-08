package dominando.android.livroandroidcap7_view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplo livro Google Android
 *
 * Criado em: 07/09/2015
 *
 * Ultima modificação: 08/09/2015
 */

public class MainActivity extends Activity {

    String[] lista = new String[] {
            "ExemploTextoCores",
            "ExemploAutoComplete",
            "ExemploImageButton",
            "ExemploToggleButton",
            "ExemploCheckRadio",
            "ExemploSpinner",
            "ExemploProgressDialog",
            "ExemploProgressBar",
            "ExemploToast",
            "ExemploAlertDialog",
            "ExemploListView",
            "ExemploListViewCostumazado",
            "ExemploGridView",
            "ExemploGallery",
            "ExemploPagerView",
            "ExemploPagerView + TitleStrip",
            "ExemploPagerView + TabStrip",
            "ImageSwitcher",
            "WebView",
            "ExemploTouchScreen",
            "Minha Canvas"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lista);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, ExemploTextoCoresActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ExemploAutoCompleteTextViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ExemploImageButtonActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ExemploToggleButtonActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ExemploCheckRadioFormActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, ExemploSpinnerActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, ExemploProgressDialogActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, ExemploProgressBarActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, ExemploToastActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, ExemploAlertDialogActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, ExemploListViewActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, ExemploListViewCostumazadoActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, ExemploGridViewActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, ExemploGalleryActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, ExemploPagerViewActivity.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, ExemploViewPagerTitleStripActivity.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this, ExemploViewPagerTabStripActivity.class));
                        break;
                    case 17:
                        startActivity(new Intent(MainActivity.this, ExemploImageSwitcherActivity.class));
                        break;
                    case 18:
                        startActivity(new Intent(MainActivity.this, ExemploWebViewActivity.class));
                        break;
                    case 19:
                        startActivity(new Intent(MainActivity.this, ExemploTouchScreenViewActivity.class));
                        break;
                    case 20:
                        startActivity(new Intent(MainActivity.this, ExemploMinhaCanvasActivity.class));
                        break;
                }
            }
        });

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista));

    }
}
