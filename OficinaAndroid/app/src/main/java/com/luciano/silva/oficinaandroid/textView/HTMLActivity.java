package com.luciano.silva.oficinaandroid.textView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.widget.TextView;

import com.luciano.silva.oficinaandroid.R;

import org.xml.sax.XMLReader;

import java.io.IOException;

/**
 * Usando recursos de HTML em um TextView, não aceita CSS apenas HTML
 *
 * Criada em: 09/10/2015
 *
 * Ultima modificação: 09/10/2015
 *
 */

public class HTMLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        TextView txtHTMLSimples = (TextView) findViewById(R.id.textViewHTMLSimples);

        final String textoSimples =
                "<html>\n" +
                "            <body>\n" +
                "                <p>\n" +
                "                    Um texto em HTML simples para ser interpretado pela TextView.\n" +
                "                </p>\n" +
                "                <p> <font color='#0000ff'>\n" +
                "                    Texto com cor azul.</font> usando tag font<br>\n" +
                "                    Texto com <b>Negrito</b>, <i>Italico</i> e <u>Sublinhado</u>\n" +
                "                </p>\n" +
                "                <p style='color='#ff0000'; backgrounf-color='#00ff00';'>\n" +
                "                    Usando estilizacao com <font color='#ff00ff'><strong>css</strong></font> não funciona.\n" +
                "                </p>\n" +
                "            </body>\n" +
                "        </html>";

        txtHTMLSimples.setText(Html.fromHtml(textoSimples));


        // para usar tags como <img> é necessario usar ImageGetter
        // vai tipo dizer onde esta essa imagem.

        TextView txtHTML = (TextView) findViewById(R.id.textViewHTML);

        final String texto =
                "<html>\n" +
                        "            <body>\n" +
                        "                <p>\n" +
                        "                    Um texto em HTML com imagens para ser interpretado pela TextView. Usando ImageGetter.\n" +
                        "                   <font color='#2f00ff'>\n" +
                        "                    Inserindo imagens:</font>\n" +
                        "                </p>\n" +
                        "                <p>\n" +
                        "                    <img src='imgHTML/android.png'>\n" +
                        "                </p>\n" +
                        "                <p>\n" +
                        "                    <figure> " +
                        "                       <img src='imgHTML/planeta_terra.png'>" +
                        "                       <figcaption>Imagem Carregada</figcapition>" +
                        "                    </figure>\n" +
                        "                </p>\n" +
                        "            </body>\n" +
                        "        </html>";

        // trata tag <img> do html
        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {

                BitmapDrawable drawable = null;

                try {
                    // por opcao arquivo vai estar na pasta Assets
                    Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open(source));

                    drawable = new BitmapDrawable(getResources(), bitmap);

                    drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return drawable;
            }
        };

        //tagHundler ultimo argumento, trata tags que o TextView nao consegue interpretar.

        txtHTML.setText(Html.fromHtml(texto, imageGetter, null));
    }
}
