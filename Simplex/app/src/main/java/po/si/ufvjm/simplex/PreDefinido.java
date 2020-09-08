package po.si.ufvjm.simplex;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PreDefinido extends Activity {

    private Double[][] matriz;
    private Intent it;
    private Simplex s;
    private String result, detalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_definido);

        String[] opcoes = new String[] {
                "Fabricar Sapato e Cinto",
                "Lista Simplex 1a",
                "Lista Simplex 1b",
                "Lista Simplex 1c",
                "Fabricar cinto M1 e M2",
                "Fabricar P1 e P2"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);
        ListView lista = (ListView) findViewById(R.id.list);

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Log.i("Depuracao", "Fabricar sapato e cinto");
                        matriz = new Double[][] {
                                {0.0,  1.0,  2.0, 3.0, 4.0,  0.0},
                                {3.0, 10.0, 12.0, 1.0, 0.0, 60.0},
                                {4.0,  2.0,  1.0, 0.0, 1.0,  6.0},
                                {0.0,  5.0,  2.0, 0.0, 0.0,  0.0} };

                        s = new Simplex();
                        s.Solution(matriz);
                        result = s.mostrarResult();
                        detalhe = s.getSolucaoDetalhada();

                        Log.i("Depuracao", "Encontrou Solucao");// + result);
                        Log.i("Depuracao", "Variavel detalhe:\n" + detalhe);

                        it = new Intent(PreDefinido.this, Resultado.class);

                        it.putExtra("simplex", result);
                        it.putExtra("detalhe", detalhe);
                        startActivity(it);

                        break;
                    case 1:
                        Log.i("Depuracao", "Lista Simplex 1a");
                        matriz = new Double[][]{
                                {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 0.0},
                                {4.0, 1.0, 2.0, 1.0, 1.0, 0.0, 6.0},
                                {5.0, 2.0, 0.0, 1.0, 0.0, 1.0, 4.0},
                                {0.0, 2.0, 1.0, 3.0, 0.0, 0.0, 0.0} };

                        s = new Simplex();
                        s.Solution(matriz);
                        result = s.mostrarResult();
                        detalhe = s.getSolucaoDetalhada();

                        Log.i("Depuracao", "String result depois Simplex: \n" + result);

                        it = new Intent(PreDefinido.this, Resultado.class);

                        it.putExtra("simplex", result);
                        it.putExtra("detalhe", detalhe);
                        startActivity(it);

                        break;
                    case 2:
                        Log.i("Depuracao", "Lista Simplex 1b");
                        matriz = new Double[][]{
                                {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 0.0},
                                {3.0, 0.2, 0.3, 1.0, 0.0, 0.0, 1.8},
                                {4.0, 0.2, 0.1, 0.0, 1.0, 0.0, 1.2},
                                {5.0, 0.3, 0.3, 0.0, 0.0, 1.0, 2.4},
                                {0.0, 5.0, 6.0, 0.0, 0.0, 0.0, 0.0} };

                        s = new Simplex();
                        s.Solution(matriz);
                        result = s.mostrarResult();
                        detalhe = s.getSolucaoDetalhada();

                        Log.i("Depuracao", "String result depois Simplex: \n" + result);

                        it = new Intent(PreDefinido.this, Resultado.class);

                        it.putExtra("simplex", result);
                        it.putExtra("detalhe", detalhe);
                        startActivity(it);

                        break;
                    case 3:
                        Log.i("Depuracao", "Lista Simplex 1c");
                        matriz = new Double[][]{
                                {0.0,  1.0,  2.0,   3.0, 4.0,  5.0, 6.0,   0.0},
                                {5.0, 20.0,  9.0,   6.0, 1.0,  1.0, 0.0,  40.0},
                                {6.0, 10.0,  4.0,   2.0, 1.0,  0.0, 1.0,  20.0},
                                {0.0, 60.0, 26.0,  15.0, 4.75, 0.0, 0.0,   0.0} };

                        s = new Simplex();
                        s.Solution(matriz);
                        result = s.mostrarResult();
                        detalhe = s.getSolucaoDetalhada();

                        Log.i("Depuracao", "String result depois Simplex: \n" + result);

                        it = new Intent(PreDefinido.this, Resultado.class);

                        it.putExtra("simplex", result);
                        it.putExtra("detalhe", detalhe);
                        startActivity(it);

                        break;
                    case 4:
                        Log.i("Depuracao", "Fabricar cinto M1 e M2");
                        matriz = new Double[][]{
                                {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0,    0.0},
                                {3.0, 2.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1000.0},
                                {4.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0,  800.0},
                                {5.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0,  400.0},
                                {6.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0,  700.0},
                                {0.0, 4.0, 3.0, 0.0, 0.0, 0.0, 0.0,    0.0} };

                        s = new Simplex();
                        s.Solution(matriz);
                        result = s.mostrarResult();
                        detalhe = s.getSolucaoDetalhada();

                        Log.i("Depuracao", "String result depois Simplex: \n" + result);

                        it = new Intent(PreDefinido.this, Resultado.class);

                        it.putExtra("simplex", result);
                        it.putExtra("detalhe", detalhe);
                        startActivity(it);

                        break;
                    case 5:
                        Log.i("Depuracao", "Fabricar P1 e P2");
                        matriz = new Double[][]{
                                {0.0,   1.0,   2.0, 3.0, 4.0, 5.0,   0.0},
                                {3.0,   2.0,   3.0, 1.0, 0.0, 0.0, 120.0},
                                {4.0,   1.0,   0.0, 0.0, 1.0, 0.0,  40.0},
                                //{5.0,   0.0,   1.0, 0.0, 0.0, 1.0,  40.0}, redundante
                                {0.0, 100.0, 150.0, 0.0, 0.0, 0.0,   0.0} };

                        s = new Simplex();
                        s.Solution(matriz);
                        result = s.mostrarResult();
                        detalhe = s.getSolucaoDetalhada();

                        Log.i("Depuracao", "String result depois Simplex: \n" + result);

                        it = new Intent(PreDefinido.this, Resultado.class);

                        it.putExtra("simplex", result);
                        it.putExtra("detalhe", detalhe);
                        startActivity(it);

                        break;
                    default:
                }
            }
        });

    }
}
