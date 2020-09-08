package com.luciano.silva.oficinaandroid.textView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.luciano.silva.oficinaandroid.R;

/**
 * Exemplo de como usar imagens png com 9-Patch para fazer as imagens
 * expandir para caber todo o texto e colocar uma imagem personalizada
 * como fundo dos TextView.
 *
 * 9 patch é dividido em 4 partes duas esticaveis (esquerda e topo) e
 * duas de conteudo (direita e baixo) e apenas aceita png
 *
 * Criada em: 09/10/2015
 *
 * Ultima modificação: 09/10/2015
 */

public class BackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
    }
}
