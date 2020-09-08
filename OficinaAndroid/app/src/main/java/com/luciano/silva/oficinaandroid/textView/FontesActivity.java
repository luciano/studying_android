package com.luciano.silva.oficinaandroid.textView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luciano.silva.oficinaandroid.R;

/**
 * Fontes que podem ser aplicadas a um TextView, EditText, Button
 * <p/>
 * Essas fontes utilizadas são arquivos .ttf, .otf, qualquer arquivo de fonte,
 * que serão carregados como objetos Typeface para serem
 * setados na propriedade Typeface do componente.

 * Criada em: 08/10/2015
 *
 * Ultima modificação: 09/10/2015
 */

public class FontesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fontes);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);

        // setar fontes diversas
        String[] fontes = getResources().getStringArray(R.array.fontes);

        // obtem quantidade de pixeis em 18sp
        //int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, getResources().getDisplayMetrics());

        for (String fonte : fontes) {
            TextView textView = new TextView(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 2, 0, 10);

            textView.setLayoutParams(layoutParams);
            String text = "Texto de exemplo de uso de Typeface personalizada com fonte " + fonte.substring(0, fonte.lastIndexOf("."))  + ": , . ? !";
            textView.setText(text);

            textView.setTextColor(Color.BLUE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
            textView.setPadding(5, 10, 5, 10);

            Typeface typeface = Typeface.createFromAsset(getAssets(), "fontes/" + fonte);
            textView.setTypeface(typeface);

            linearLayout.addView(textView);
        }
        String fonte = "DK Petit Four.otf";

        // adicionando outros componentes
        EditText editText = new EditText(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 2, 0, 10);

        editText.setLayoutParams(layoutParams);
        String text = "Texto com fonte " + fonte.substring(0, fonte.lastIndexOf("."));
        editText.setHint(text);

        editText.setTextColor(Color.BLUE);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
        editText.setPadding(5, 10, 5, 10);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fontes/" + fonte);
        editText.setTypeface(typeface);

        linearLayout.addView(editText);

        // button
        Button button = new Button(this);

        button.setLayoutParams(layoutParams);
        button.setText(text);

        button.setTextColor(Color.BLUE);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
        button.setPadding(5, 10, 5, 10);

        button.setTypeface(typeface);

        linearLayout.addView(button);

    }
}
