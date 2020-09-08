package po.si.ufvjm.simplex.po.si.ufvjm.simplex.matrizes;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;

import po.si.ufvjm.simplex.R;

public class Resultado extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutp);
        layout.setPadding(10, 10, 10, 10);
        layout.setBackgroundColor(0xffffffff);

        LinearLayout.LayoutParams paramsET = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        final TextView linha1TV = new TextView(this);
        linha1TV.setLayoutParams(paramsET);
        linha1TV.setTextColor(0xff000000);
        linha1TV.setText("Linha 1: ");
        linha1TV.setPadding(10, 15, 15, 10);

        final EditText linha1 = new EditText(this);
        linha1.setLayoutParams(paramsET);
        linha1.setSingleLine(true);
        linha1.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);

        final TextView linha2TV = new TextView(this);
        linha2TV.setLayoutParams(paramsET);
        linha2TV.setTextColor(0xff000000);
        linha2TV.setText("Linha 2: ");
        linha2TV.setPadding(10, 15, 15, 10);
        final EditText linha2 = new EditText(this);
        linha2.setLayoutParams(paramsET);
        linha2.setSingleLine(true);
        linha2.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);



        final LinearLayout layout2 = new LinearLayout(this);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout2.setLayoutParams(layoutp2);
        layout2.setPadding(10, 10, 10, 10);
        layout2.setBackgroundColor(0xffffffff);

        final TextView multTV = new TextView(this);
        multTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        multTV.setTextColor(0xff000000);
        multTV.setText("Multiplicar linha 2 por: ");
        multTV.setPadding(10, 15, 15, 10);
        final EditText multiplicador = new EditText(this);
        multiplicador.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        multiplicador.setSingleLine(true);
        multiplicador.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        final String resultado = "";
        final Button btOK = new Button(this);
        btOK.setText("Ok");
        btOK.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String l1 = linha1.getText().toString();
                String l2 = linha2.getText().toString();
                double multi = Double.parseDouble(multiplicador.getText().toString());

                int numLinhas = 0;
                Scanner sl1 = new Scanner(l1);
                Scanner sl2 = new Scanner(l2);
                ArrayList<Double> dl1 = new ArrayList<Double>();
                ArrayList<Double> dl2 = new ArrayList<Double>();
                ArrayList<Double> dlR = new ArrayList<Double>();

                while (sl1.hasNextDouble()) {
                    dl1.add(sl1.nextDouble());
                    dl2.add(sl2.nextDouble());
                    dlR.add(dl1.get(numLinhas) + (dl2.get(numLinhas) * multi));
                    ++numLinhas;
                }


                for (Double d : dl1) {

                    resultado.concat(String.valueOf(d));

                }
                resultado.concat("\n");

            }
        });


        final TextView linha3TV = new TextView(this);
        linha3TV.setLayoutParams(paramsET);
        linha3TV.setTextColor(0xff000000);
        linha3TV.setText("Resultado: ");
        linha3TV.setPadding(10, 15, 15, 10);
        final TextView linhaR = new TextView(this);
        linhaR.setLayoutParams(paramsET);
        linhaR.setText(resultado);




        layout.addView(linha1TV);
        layout.addView(linha1);
        layout.addView(linha2TV);
        layout.addView(linha2);

        layout2.addView(multTV);
        layout2.addView(multiplicador);
        layout2.addView(btOK);

        layout.addView(layout2);

        layout.addView(linha3TV);
        layout.addView(linhaR);

        setContentView(layout);
    }
}
