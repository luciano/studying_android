package com.luciano.silva.jwnotebook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * JW Notebook
 *
 * Editor de texto para anotação de discursos
 * Usando layout simples e intuitivo com auxilios para textos biblicos.
 *
 * Projeto iniciado em 09 de Setembro de 2015
 *
 * @author Luciano Silva
 *
 */

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Context context = this;

    // erro no mateus 1:1
    // erro em salmos 1:1
    // erro gen 1:1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

//        mToolbar.setTitleTextColor(Color.WHITE);

//        final EditText mEditText = (EditText) findViewById(R.id.editText);
//
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                TextView textView = (TextView) findViewById(R.id.textView);
//
//                String busca = mEditText.getText().toString();
//
////                String teste = encontrarTexto("sal 55:22");
//  //              String testeFind = buscaTexto(teste);
//
//                if (!busca.trim().isEmpty()) {
//                    String texto = buscaTexto(busca);
//                    if (texto != null)
//                        textView.setText(texto);
//                    //else {
//                        //mEditText.setError("Texto não encontrado!");
//
//                    //}
//
//                }
//            }
//        });


    }

    private String buscaTexto(String versiculo) {

        //  1 - 18 bibleOld.nwt
        // 19 - 39 bibleOldS.nwt
        // 40 - 66 bibleNew.nwt
        int livro = Integer.parseInt(versiculo.substring(1, versiculo.indexOf("_")));

        String path;

        if (livro >= 1 && livro <= 18) {
            path = "bibleOld.nwt";
        } else if (livro >= 19 && livro <= 39) {
            path = "bibleOldS.nwt";
        } else if (livro >= 40 && livro <= 66) {
            path = "bibleNew.nwt";
        } else {
            return null;
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(getAssets().open(path)));

            // teste
//            if (versiculo.contains("v30")) {
//                in.skip(90000);
//            }

            String linha;
            while ((linha = in.readLine()) != null) {
                if (linha.contains(versiculo)) {
                    return linha.replaceAll("<" + versiculo + ">", "");
                }
//                if (linha.startsWith("<" + versiculo + ">")) {
//                    return linha.replaceAll("<" + versiculo + ">", "");
//                }
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//    private String encontrarTexto(String versiculo) {
//
//        String vers = null;
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(getAssets().open("livros.nwt")));
//
//            int count = 0;
//
//
//            String linha;
//            boolean continua = true;
//
//            String livro = versiculo.replaceAll("\\d:\\d", "").trim();
//            String capitulo = versiculo.replaceAll("(\\d)?(\\s)?(\\w{1,})(\\s)?(\\d):(\\d)", "$5").trim();
//            String mVersiculo = versiculo.replaceAll("(\\d)?(\\s)?(\\w{1,})(\\s)?(\\d):(\\d)", "$6").trim();
//
//            while ((linha = in.readLine()) != null && continua) {
//
//                ++count;
//
//                if (linha.toLowerCase().contains(livro)) {
//                    continua  = false;
//                }
//            }
//
//            if (linha != null) {
//
//                vers = "v" + count + "_" + capitulo + "_" + mVersiculo;
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return vers;
//    }

    MenuItem itemAdd;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        itemAdd = menu.findItem(R.id.action_new);

        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setQueryHint(getString(R.string.action_search));
        mSearchView.setOnQueryTextListener(OnQueryListener());

        return true;
    }

    private SearchView.OnQueryTextListener OnQueryListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // edicao

                String text = TextoBiblico.texto(query);
                String path = TextoBiblico.pathTexto();

                //=================================================
                String s = buscaTexto(path);

                if (s == null) {
                    s = "Esse texto não existe!";
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(text).setMessage(s);

                builder.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}});

                builder.create().show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_new) {
            //Toast.makeText(MainActivity.this, "Novo Discurso", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, EscritorActivity.class));
            return true;
        }

        if (id == R.id.action_sobre) {
            //Toast.makeText(MainActivity.this, "Sobre App", Toast.LENGTH_SHORT).show();
            String s = "Aplicativo desenvolvido para auxiliar em anotações de discursos.\n\nDesenvolvido por Luciano Silva";

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Sobre JW Notebook").setMessage(s);

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            //builder.setNegativeButton("");

            builder.create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
