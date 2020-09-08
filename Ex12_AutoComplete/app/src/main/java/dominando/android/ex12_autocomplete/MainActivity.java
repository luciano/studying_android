package dominando.android.ex12_autocomplete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Exemplo livro Dominando Android
 *
 * Criado em: 25/08/2015
 *
 * Ultima modificação: 25/08/2015
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> cidade = new ArrayList<>();
        cidade.add("Belo Horizonte");
        cidade.add("Contagem");
        cidade.add("Curvelo");
        cidade.add("Diamantina");
        cidade.add("Montes Claros");
        cidade.add("Sete Lagoas");
        cidade.add("Ouro Preto");
        cidade.add("Três Marias");
        cidade.add("São Paulo");
        cidade.add("Santa Luzia");
        cidade.add("Santa Cruz");
        cidade.add("Santos");
        cidade.add("Recife");

        // se nao tratar as letras acentuadas elas nao sao
        // oferecidas quando escrever sem acento.
        // tratando esses acentos com uma classe filtro
        MeuAutoCompleteAdapter adapter = new MeuAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line, cidade);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
    }
}
