package com.luciano.silva.oficinaandroid.textView;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.luciano.silva.oficinaandroid.R;

/**
 * Mostra recurso de sublinhar, riscar texto e tambem numero maximo de linhas
 * para um TextView usando maxLines e Ellipsize
 *
 * Criada em: 09/10/2015
 *
 * Ultima modificação: 09/10/2015
 */

public class PaintFlagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_flag);

        TextView mTextViewRiscado = (TextView) findViewById(R.id.textViewRiscado);
        mTextViewRiscado.setPaintFlags(mTextViewRiscado.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TextView mTextViewSublinhado = (TextView) findViewById(R.id.textViewSublinhado);
        mTextViewSublinhado.setPaintFlags(mTextViewSublinhado.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
