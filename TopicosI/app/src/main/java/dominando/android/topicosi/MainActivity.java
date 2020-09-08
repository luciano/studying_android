package dominando.android.topicosi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etPrecoUnitario;
    private EditText etCustoFixo;
    private EditText etCustoVariavel;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        View layout = LayoutInflater.from(this).inflate(R.layout.activity_main, null);

        etPrecoUnitario = (EditText) layout.findViewById(R.id.precoVariavel);
        etCustoFixo = (EditText) layout.findViewById(R.id.custoFixo);
        etCustoVariavel = (EditText) layout.findViewById(R.id.custoVariavel);
        tvResultado = (TextView) layout.findViewById(R.id.resultado);

        ScrollView mScrollView = new ScrollView(this);
        mScrollView.addView(layout);
        setContentView(mScrollView);
    }

    public void onCalcular(View view) {
        double precoUnitario = Double.parseDouble(etPrecoUnitario.getText().toString());
        double custoFixo = Double.parseDouble(etCustoFixo.getText().toString());
        double custoVariavel = Double.parseDouble(etCustoVariavel.getText().toString());

        StringBuilder builder = new StringBuilder();

        double margemContribuicao = (precoUnitario - custoVariavel);
        double quantidadeEquilibrio = custoFixo / margemContribuicao;
        double receitaTotal = precoUnitario * quantidadeEquilibrio;
        double custoTotal = custoFixo + (custoVariavel * quantidadeEquilibrio);

        builder.append("Quantidade Equilibrio: " + quantidadeEquilibrio);
        builder.append("\n\n");

        builder.append("Margem de Contribuição: " + margemContribuicao);
        builder.append("\n\n");

        builder.append("Receita Total: " + receitaTotal);
        builder.append("\n\n");

        builder.append("Custo Total: " + custoTotal);
        builder.append("\n\n");

        builder.append("\n\nAdicionando um na Quantidade:\n\n");

        ++quantidadeEquilibrio;
        receitaTotal = precoUnitario * quantidadeEquilibrio;
        custoTotal = custoFixo + (custoVariavel * quantidadeEquilibrio);

        builder.append("Receita Total: " + receitaTotal);
        builder.append("\n\n");

        builder.append("Custo Total: " + custoTotal);
        builder.append("\n\n");

        tvResultado.setText(builder.toString());
    }
}
