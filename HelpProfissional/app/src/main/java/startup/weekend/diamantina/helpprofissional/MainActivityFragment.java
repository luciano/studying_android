package startup.weekend.diamantina.helpprofissional;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivityFragment extends Fragment {

    //private TextView mImageView;
    private HashMap<String, List<String>> dados;
    private List<String> keys;
    private Context context;
    private int cor;
    private ExpandableListView mExpandableListView;
    private ListView mListView;
    private MainExpandableAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //mImageView = (ImageView) view.findViewById(R.id.imgPropaganda);
        //mImageView = (TextView) view.findViewById(R.id.imgPropaganda);
        mListView = (ListView) view.findViewById(R.id.listView);
        mExpandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);

        context = getActivity();
        // dados = getDados();
        dados = getDadosDB();
        keys = new ArrayList<>(dados.keySet());

        mAdapter = new MainExpandableAdapter(dados);

        mExpandableListView.setAdapter(mAdapter);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // Snackbar.make(v, "Clicado em: " + keys.get(groupPosition) + " > " + dados.get(keys.get(groupPosition)).get(childPosition), Snackbar.LENGTH_SHORT).show();

                Bundle params = new Bundle();

                // SER MAIS INTELIGENTE AQUI PLEASE!!!!

                // BUSCAR DIRETO NO HASHMAP O QUE CLICOU E ENVIAR PRA ACTIVITY
                // REDUZ LINHAS DE CODIGO E MANUTENCAO

                String profissional = dados.get(keys.get(groupPosition)).get(childPosition);
                params.putString("PROFISSIONAL", profissional);

//                /*
//                // escolher activity para iniciar
//                switch (keys.get(groupPosition)) {
//
//                    case "Tecnico em Informatica":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Tecnico em Informatica 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Tecnico em Informatica 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Tecnico em Informatica 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Tecnico em Informatica 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Tecnico em Informatica 5");
//                                break;
//                        }
//                        break;
//                    case "Professores Particulares":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Professores Particulares 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Professores Particulares 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Professores Particulares 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Professores Particulares 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Professores Particulares 5");
//                                break;
//                        }
//                        break;
//                    case "Encanador":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Encanador 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Encanador 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Encanador 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Encanador 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Encanador 5");
//                                break;
//                        }
//                        break;
//                    case "Pedreiro":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Pedreiro 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Pedreiro 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Pedreiro 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Pedreiro 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Pedreiro 5");
//                                break;
//                        }
//                        break;
//                    case "Carpinteiro":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Carpinteiro 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Carpinteiro 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Carpinteiro 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Carpinteiro 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Carpinteiro 5");
//                                break;
//                        }
//                        break;
//                    case "Montador de Móveis":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Montador de Móveis 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Montador de Móveis 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Montador de Móveis 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Montador de Móveis 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Montador de Móveis 5");
//                                break;
//                        }
//                        break;
//                    case "Mecanico":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Mecanico 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Mecanico 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Mecanico 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Mecanico 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Mecanico 5");
//                                break;
//                        }
//                        break;
//                    case "Eletricista":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Eletricista 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Eletricista 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Eletricista 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Eletricista 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Eletricista 5");
//                                break;
//                        }
//                        break;
//                    case "Carretos":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Carretos 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Carretos 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Carretos 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Carretos 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Carretos 5");
//                                break;
//                        }
//                        break;
//                    case "Costureira":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Costureiras 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Costureiras 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Costureiras 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Costureiras 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Costureiras 5");
//                                break;
//                        }
//                        break;
//                    case "Manicure":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Manicure 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Manicure 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Manicure 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Manicure 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Manicure 5");
//                                break;
//                        }
//                        break;
//                    case "Faxineira":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Faxineira 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Faxineira 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Faxineira 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Faxineira 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Faxineira 5");
//                                break;
//                        }
//                        break;
//                    case "Taxi":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Taxi 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Taxi 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Taxi 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Taxi 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Taxi 5");
//                                break;
//                        }
//                        break;
//                    case "Tecnico de Celulares":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Tecnico de Celulares 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Tecnico de Celulares 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Tecnico de Celulares 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Tecnico de Celulares 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Tecnico de Celulares 5");
//                                break;
//                        }
//                        break;
//                    case "Tecnico de Eletronicos":
//                        switch (childPosition) {
//                            case 0:
//                                params.putString("PROFISSIONAL", "Tecnico de Eletronicos 1");
//                                break;
//                            case 1:
//                                params.putString("PROFISSIONAL", "Tecnico de Eletronicos 2");
//                                break;
//                            case 2:
//                                params.putString("PROFISSIONAL", "Tecnico de Eletronicos 3");
//                                break;
//                            case 3:
//                                params.putString("PROFISSIONAL", "Tecnico de Eletronicos 4");
//                                break;
//                            case 4:
//                                params.putString("PROFISSIONAL", "Tecnico de Eletronicos 5");
//                                break;
//                        }
//                        break;
//                }
//


                //startActivity(new Intent(context, DetalhesProfissionalActivity.class).putExtras(params));
                startActivity(new Intent(context, DetalhesProfissionalActivity.class).putExtras(params));

                return true;
            }
        });

        // postPropaganda();

        setHasOptionsMenu(true);

        // setar um selector para cada estado!
        // mExpandableListView.setGroupIndicator(null);

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//
//        //if (getResources().getConfiguration().keyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {
////        if (isSoftKeyboardShowing(getActivity())) {
////            mImageView.setVisibility(View.GONE);
////        } else {
////            mImageView.setVisibility(View.VISIBLE);
////        }
//
//
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem menuItemSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(menuItemSearch);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                buscaProfissional(newText);
                return false;
            }
        });
        mSearchView.setQueryHint("Qual sua necessidade?");

        MenuItemCompat.setOnActionExpandListener(menuItemSearch, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                limparBusca();
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_cadastrar) {
            startActivity(new Intent(context, CadastroActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void buscaProfissional(String query) {
        ArrayList<String> categorias = new ArrayList<>(keys);

        if (query == null || query.trim().equals("")) {
            limparBusca();
            return;
        }

        HashMap<String, List<String>> map = new HashMap<>(dados);

        //mExpandableListView.setVisibility(View.GONE);
        for (int i = categorias.size() - 1; i >= 0; --i) {
            if (!categorias.get(i).toLowerCase().contains(query.toLowerCase())) {
                map.remove(categorias.get(i));
                categorias.remove(i);
            }
        }

        //mListView.setVisibility(View.VISIBLE);
        //mListView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, categorias));
        //mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    @Override
          //  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //}
       // });



        MainExpandableAdapter mAdapter = new MainExpandableAdapter(map);

        mExpandableListView.setAdapter(mAdapter);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Bundle params = new Bundle();
                String profissional = dados.get(keys.get(groupPosition)).get(childPosition);
                params.putString("PROFISSIONAL", profissional);

                startActivity(new Intent(context, DetalhesProfissionalActivity.class).putExtras(params));
                return true;
            }
        });

    }

    private void limparBusca() {
        mListView.setVisibility(View.GONE);
        mExpandableListView.setAdapter(mAdapter);
        mExpandableListView.setVisibility(View.VISIBLE);
    }


    void postPropaganda() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cor++;
                int color = Color.BLUE;
                if (cor % 3 == 0) {
                    color = Color.GRAY;
                } else if (cor % 3 == 1) {
                    color = Color.MAGENTA;
                } else if (cor % 3 == 2) {
                    color = Color.YELLOW;
                }
                //               mImageView.setBackgroundColor(color);

                postPropaganda();
            }
        }, 5000);
    }

    private HashMap<String, List<String>> getDados() {

        HashMap<String, List<String>> listHashMap = new HashMap<>();

//--------------------------------------------------------------------------------------------------

        List<String> listTec = new ArrayList<>();
        listTec.add("Tecnico 1");
        listTec.add("Tecnico 2");
        listTec.add("Tecnico 3");
        listTec.add("Tecnico 4");
        listTec.add("Tecnico 5");


        List<String> listProf = new ArrayList<>();
        listProf.add("Professor de Matematica");
        listProf.add("Professor Portugues");
        listProf.add("Professor Ingles");
        listProf.add("Professor Calculo");
        listProf.add("Professor Fisica");

        List<String> listEncanador = new ArrayList<>();
        listEncanador.add("Encanador 1");
        listEncanador.add("Encanador 2");
        listEncanador.add("Encanador 3");
        listEncanador.add("Encanador 4");
        listEncanador.add("Encanador 5");

        List<String> listPedreiro = new ArrayList<>();
        listPedreiro.add("Pedreiro 1");
        listPedreiro.add("Pedreiro 2");
        listPedreiro.add("Pedreiro 3");
        listPedreiro.add("Pedreiro 4");
        listPedreiro.add("Pedreiro 5");

        List<String> listCarpinteiro = new ArrayList<>();
        listCarpinteiro.add("Carpinteiro 1");
        listCarpinteiro.add("Carpinteiro 2");
        listCarpinteiro.add("Carpinteiro 3");
        listCarpinteiro.add("Carpinteiro 4");
        listCarpinteiro.add("Carpinteiro 5");

        List<String> listMontador = new ArrayList<>();
        listMontador.add("Montador de Móveis 1");
        listMontador.add("Montador de Móveis 2");
        listMontador.add("Montador de Móveis 3");
        listMontador.add("Montador de Móveis 4");
        listMontador.add("Montador de Móveis 5");

        List<String> listMecanico = new ArrayList<>();
        listMecanico.add("Mecanico 1");
        listMecanico.add("Mecanico 2");
        listMecanico.add("Mecanico 3");
        listMecanico.add("Mecanico 4");
        listMecanico.add("Mecanico 5");

        List<String> listEletricista = new ArrayList<>();
        listEletricista.add("Eletricista 1");
        listEletricista.add("Eletricista 2");
        listEletricista.add("Eletricista 3");
        listEletricista.add("Eletricista 4");
        listEletricista.add("Eletricista 5");

        List<String> listCarreto = new ArrayList<>();
        listCarreto.add("Carreto 1");
        listCarreto.add("Carreto 2");
        listCarreto.add("Carreto 3");
        listCarreto.add("Carreto 4");
        listCarreto.add("Carreto 5");

        List<String> listCostureira = new ArrayList<>();
        listCostureira.add("Costureira 1");
        listCostureira.add("Costureira 2");
        listCostureira.add("Costureira 3");
        listCostureira.add("Costureira 4");
        listCostureira.add("Costureira 5");

        List<String> listFaxineira = new ArrayList<>();
        listFaxineira.add("Faxineira 1");
        listFaxineira.add("Faxineira 2");
        listFaxineira.add("Faxineira 3");
        listFaxineira.add("Faxineira 4");
        listFaxineira.add("Faxineira 5");

        List<String> listManicure = new ArrayList<>();
        listManicure.add("Manicure 1");
        listManicure.add("Manicure 2");
        listManicure.add("Manicure 3");
        listManicure.add("Manicure 4");
        listManicure.add("Manicure 5");

        List<String> listTaxi = new ArrayList<>();
        listTaxi.add("Taxi 1");
        listTaxi.add("Taxi 2");
        listTaxi.add("Taxi 3");
        listTaxi.add("Taxi 4");
        listTaxi.add("Taxi 5");

        List<String> listConsertoCell = new ArrayList<>();
        listConsertoCell.add("Tecnico de Celular 1");
        listConsertoCell.add("Tecnico de Celular 2");
        listConsertoCell.add("Tecnico de Celular 3");
        listConsertoCell.add("Tecnico de Celular 4");
        listConsertoCell.add("Tecnico de Celular 5");

        List<String> listConsertoEletros = new ArrayList<>();
        listConsertoEletros.add("Tecnico de Eletronicos 1");
        listConsertoEletros.add("Tecnico de Eletronicos 2");
        listConsertoEletros.add("Tecnico de Eletronicos 3");
        listConsertoEletros.add("Tecnico de Eletronicos 4");
        listConsertoEletros.add("Tecnico de Eletronicos 5");

//--------------------------------------------------------------------------------------------------
        listHashMap.put("Tecnico em Informatica", listTec);
        listHashMap.put("Professores Particulares", listProf);
        listHashMap.put("Encanador", listEncanador);
        listHashMap.put("Pedreiro", listPedreiro);
        listHashMap.put("Carpinteiro", listCarpinteiro);
        listHashMap.put("Montador de Móveis", listMontador);
        listHashMap.put("Mecanico", listMecanico);
        listHashMap.put("Eletricista", listEletricista);
        listHashMap.put("Carretos", listCarreto);
        listHashMap.put("Costureira", listCostureira);
        listHashMap.put("Faxineira", listFaxineira);
        listHashMap.put("Manicure", listManicure);
        listHashMap.put("Taxi", listTaxi);
        listHashMap.put("Tecnico de Celulares", listConsertoCell);
        listHashMap.put("Tecnico de Eletronicos", listConsertoEletros);

        return listHashMap;
    }

    private HashMap<String, List<String>> getDadosDB() {

        HashMap<String, List<String>> listHashMap = new HashMap<>();

        // 0 - categoria
        // 1 - n nomes autonomos

        // ler arquivo da pasta asstes
        String path = "categorias.db";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(context.getAssets().open(path)));

            String linha = null;
            while ((linha = in.readLine()) != null) {
                String[] dados = linha.split(";");

                String categoria = dados[0];

                List<String> nomesAutonomos = new ArrayList<>(Arrays.asList(dados));
                nomesAutonomos.remove(0); // tira categoria

                if (categoria != null && !categoria.trim().isEmpty() && !nomesAutonomos.isEmpty())
                    listHashMap.put(categoria, nomesAutonomos);

            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listHashMap;
    }
}
