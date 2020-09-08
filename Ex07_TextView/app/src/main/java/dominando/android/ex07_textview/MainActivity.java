package dominando.android.ex07_textview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Exemplo livro Dominando Android
 *
 * Criado em: 20/08/2015
 *
 * Ultima modificação: 20/08/2015
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface typefaceLight = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");

        Typeface typefaceCondensed = Typeface.createFromAsset(getAssets(), "RobotoCondensed-Regular.ttf");

        TextView txt1 = (TextView) findViewById(R.id.txtFonte1);
        txt1.setTypeface(typefaceLight);

        TextView txt2 = (TextView) findViewById(R.id.txtFonte2);
        txt2.setTypeface(typefaceCondensed);

        TextView txt3 = (TextView) findViewById(R.id.txtStrike);
        txt3.setPaintFlags(txt3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TextView txtHtml = (TextView) findViewById(R.id.txtHtml);

        final String textoEmHtml =
                "<html><body>Html em " +
                "<b>Negrito</b>, <i>Itálico</i>" +
                " e <u>Sublinhado</u><br>" +
                "Mario: <img src='mario.png' /><br>" +
                "Luigi: <img src='yoshi.png' /><br>" +
                "Um texto depois da imagem</body></html>";

        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {

                BitmapDrawable drawable = null;

                try {

                    Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open(source));
                    drawable = new BitmapDrawable(getResources(), bitmap);

                    drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return drawable;
            }
        };

        txtHtml.setText(Html.fromHtml(textoEmHtml, imageGetter, null));

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
