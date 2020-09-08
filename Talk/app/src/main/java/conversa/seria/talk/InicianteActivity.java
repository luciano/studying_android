package conversa.seria.talk;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class InicianteActivity extends Arquivo implements AdapterView.OnItemClickListener{

    private String[] strings;
    private ListView lista;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        strings = TextUtils.split(leitura("iniciante"), "\n");

        Log.i(MainActivity.CATEGORIA, "Leitura: " + Arrays.toString(strings));

        lista = new ListView(this);

        //ArrayAdapter<String>
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        Log.i(MainActivity.CATEGORIA, "depois ArrayAdapter: " + adapter.getCount());

        lista.setAdapter(adapter);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        params.weight = 1;

        lista.setLayoutParams(params);

        lista.setOnItemClickListener(this);

        LinearLayout layout = ListaVisual.getListaVisual(this);

        layout.addView(lista);

        ImageButton imageButton = ListaVisual.getImageButton(this);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addConteudo(v);
            }
        });

        layout.addView(imageButton);
        setContentView(layout);
    }

    public void addConteudo(View v) {
        startActivity(new Intent(this, AddActivity.class).putExtra("tabela", "iniciante"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String titulo = lista.getItemAtPosition(position).toString();

        TextView texto = new TextView(InicianteActivity.this);
        texto.setText(leitura(titulo));
        texto.setBackgroundColor(Color.WHITE);
        texto.setTextColor(Color.BLACK);
        texto.setPadding(16, 16, 16, 16);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(3, 3, 3, 3);

        params.gravity = Gravity.CENTER;

        texto.setLayoutParams(params);

        setContentView(texto);
    }
}
