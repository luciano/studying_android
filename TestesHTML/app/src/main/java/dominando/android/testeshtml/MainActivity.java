package dominando.android.testeshtml;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context = this;
    private PageAdapter adapter;
    private List<Page> list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listView.setEmptyView(findViewById(android.R.id.empty));

        list = getListHTML();

        adapter = new PageAdapter(context, list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("page", list.get(position));
                startActivity(intent);
            }
        });

        TextView footer = new TextView(this);
        footer.setPadding(5, 15, 5, 15);
        footer.setBackgroundColor(Color.TRANSPARENT);
        footer.setTextColor(Color.WHITE);
        footer.setGravity(Gravity.RIGHT|Gravity.BOTTOM);
        footer.setText(getResources().getQuantityString(R.plurals.paginas, adapter.getCount(), adapter.getCount()));
        listView.addFooterView(footer);

        listView.setAdapter(adapter);
    }

    // tentar usar pre
    // ou as < = &lt;
    //> = &gt;
    // pra mostrar codigo no texto

    private List<Page> getListHTML() {
        List<Page> lista = new ArrayList<Page>();
        lista.add(new Page("Index", "file:///android_asset/capitulo-2/index.html", "02/09/2015"));
        lista.add(new Page("Primeiras TAGs", "file:///android_asset/capitulo-2/primeiras-tags.html", "02/09/2015"));
        lista.add(new Page("Estilizando com CSS", "file:///android_asset/capitulo-2/estilizando-com-css.html", "02/09/2015"));
        lista.add(new Page("Estilo com arquivo externo", "file:///android_asset/capitulo-2/estilizando-arquivo-externo.html", "02/09/2015"));
        lista.add(new Page("Alinhamento e Decoração texto", "file:///android_asset/capitulo-2/alinhamento-e-demoracao-texto.html", "02/09/2015"));
        lista.add(new Page("Listas HTML", "file:///android_asset/capitulo-2/listas-html.html", "02/09/2015"));
        lista.add(new Page("Espaçamento Margem e Links", "file:///android_asset/capitulo-2/espacamento-margem-links.html", "03/09/2015"));
        lista.add(new Page("Fluxo do documento", "file:///android_asset/capitulo-2/fluxo-documento.html", "03/09/2015"));
        lista.add(new Page("HTML semântico", "file:///android_asset/capitulo-3/html-semantico.html", "03/09/2015"));
        lista.add(new Page("Estilização com classes", "file:///android_asset/capitulo-3/estilizacao-com-classes.html", "03/09/2015"));
        lista.add(new Page("Reset, Block e Inline", "file:///android_asset/capitulo-3/reset-e-block-inline.html", "03/09/2015"));
        lista.add(new Page("Position", "file:///android_asset/capitulo-3/position.html", "03/09/2015"));
        lista.add(new Page("Formularios", "file:///android_asset/capitulo-4/formularios.html", "04/09/2015"));
        lista.add(new Page("Decoração de texto", "file:///android_asset/capitulo-4/decoracao-texto.html", "04/09/2015"));
        lista.add(new Page("Seletores de Atributos CSS", "file:///android_asset/capitulo-4/seletores-atributos.html", "04/09/2015"));
        lista.add(new Page("Substituição por imagem", "file:///android_asset/capitulo-4/substituicao-imagem.html", "04/09/2015"));
        lista.add(new Page("JavaScript Introdução", "file:///android_asset/capitulo-5/javascript-introducao.html", "04/09/2015"));
        lista.add(new Page("Interatividade Web", "file:///android_asset/capitulo-5/interatividade-web.html", "04/09/2015"));
        lista.add(new Page("Funções Temporais", "file:///android_asset/capitulo-5/funcao-temporal.html", "04/09/2015"));
        lista.add(new Page("Seletores CSS Avançados", "file:///android_asset/capitulo-6/seletores-avancados.html", "05/09/2015"));
        lista.add(new Page("Pseudo-Classes", "file:///android_asset/capitulo-6/pseudo-classes.html", "05/09/2015"));
        // lista.add(new Page("", "file:///android_asset/capitulo-6/", "05/09/2015"));
        // lista.add(new Page("", "file:///android_asset/capitulo-6/", "05/09/2015"));
        // lista.add(new Page("", "file:///android_asset/capitulo-6/", "05/09/2015"));

        return lista;
    }

}
