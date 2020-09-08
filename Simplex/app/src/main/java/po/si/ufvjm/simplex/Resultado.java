package po.si.ufvjm.simplex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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


        Intent it = getIntent();
        final Bundle b = it.getExtras();
        if(b != null) {

            TextView tv = new TextView(Resultado.this);
            tv.setText(b.getString("simplex"));
            tv.setTextSize(18);
            tv.setTextColor(0xff000000);
            tv.setPadding(20, 10, 10, 20);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            tv.setLayoutParams(lp);
            layout.addView(tv);

            Button bt = new Button(this);
            bt.setText("Solução Detalhada");
            bt.setTextColor(0xff000000);
            bt.setPadding(10, 10, 10, 10);

            lp.gravity = Gravity.BOTTOM;
            lp.weight = 0;
            bt.setLayoutParams(lp);
            layout.addView(bt);

            //interessanter ter scroll para resultados grandes
            //ter um scroll hrizontal e um linear layout horizontal talvez
            //usar proprio layout

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Depuracao", "Entrou OnClick");
                    final LinearLayout layout1 = new LinearLayout(Resultado.this);
                    layout1.setOrientation(LinearLayout.VERTICAL);
                    layout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    layout1.setPadding(10, 10, 10, 10);
                    layout1.setBackgroundColor(0xffffffff);

                    Log.i("Depuracao", "Criou layout1");

                    TextView tv1 = new TextView(Resultado.this);
                    tv1.setText("Resolução:");
                    tv1.setTextSize(18);
                    tv1.setTextColor(0xff000000);
                    tv1.setPadding(20, 10, 10, 20);
                    tv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    layout1.addView(tv1);

                    ScrollView sv = new ScrollView(Resultado.this);
                    sv.addView(layout1);
                    Log.i("Depuracao", "Criou sv");

                    final LinearLayout layout2 = new LinearLayout(Resultado.this);
                    layout2.setOrientation(LinearLayout.HORIZONTAL);
                    layout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    layout2.setPadding(10, 10, 10, 10);
                    layout2.setBackgroundColor(0xffffffff);
                    Log.i("Depuracao", "Criou layout2");

                    HorizontalScrollView hsv = new HorizontalScrollView(Resultado.this);
                    hsv.addView(layout2);
                    Log.i("Depuracao", "Criou hsv");

                    layout1.addView(hsv);

                    TextView dados = new TextView(Resultado.this);
                    dados.setText(b.getString("detalhe"));
                    dados.setTextSize(18);
                    dados.setTextColor(0xff000000);
                    dados.setPadding(20, 10, 10, 20);
                    dados.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    layout2.addView(dados);

                    TextView tv2 = new TextView(Resultado.this);
                    tv2.setText(b.getString("simplex"));
                    tv2.setTextSize(18);
                    tv2.setTextColor(0xff000000);
                    tv2.setPadding(20, 10, 10, 20);
                    tv2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    layout1.addView(tv2);

                    setContentView(sv);
                }
            });

        } else {
            Log.i("Depuracao","Activity Resultado: Erro na passagem do parametro na intent.");
        }

        ScrollView sv = new ScrollView(Resultado.this);
        sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sv.setBackgroundColor(0xffffffff);
        sv.addView(layout);

        setContentView(sv);

    }//fim metodo onCreate
}
