package dominando.android.livroandroidcap6_layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int layout = intent.getIntExtra("layout", R.layout.activity_layout);
        if (layout > 2) {
            setContentView(layout);
            if (layout == R.layout.exemplo_scroll_view) {

                LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout);

                for (int i = 0; i < 100; ++i) {
                    // instanciando pela API
                    /*
                    TextView textView = new TextView(this);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    textView.setPadding(5, 15, 15, 5);
                    textView.setText("Texto: " + i);
                    */

                    // instanciando inflando XML
                    LayoutInflater inflater = LayoutInflater.from(this);
                    TextView textView = (TextView) inflater.inflate(R.layout.inflate_textview, layout1, false);
                    textView.setText("Texto: " + i);

                    layout1.addView(textView);
                }
            }
        } else {
            // LinearLayout API
            if (layout == 1) {
                // cria o layout
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                linearLayout.setPadding(10, 10, 10, 10);

                TextView nome = new TextView(this);
                nome.setText("Nome: ");
                nome.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(nome);

                EditText tnome = new EditText(this);
                tnome.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(tnome);
                tnome.requestFocus();

                TextView senha = new TextView(this);
                senha.setText("Senha: ");
                senha.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(senha);

                EditText tsenha = new EditText(this);
                tsenha.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(tsenha);

                Button ok = new Button(this);
                ok.setText("Ok");
                ok.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ok.setGravity(Gravity.RIGHT);
                linearLayout.addView(ok);

                setContentView(linearLayout);
            }
            // tableLayout
            else {

                TableLayout tableLayout = new TableLayout(this);
                tableLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                //expande coluna 1
                tableLayout.setColumnShrinkable(1, true);

                // linha 1
                TableRow linha1 = new TableRow(this);
                TextView nome = new TextView(this);
                nome.setText("Nome: ");
                linha1.addView(nome);

                EditText tnome = new EditText(this);
                tnome.requestFocus();
                linha1.addView(tnome);

                // linha 2
                TableRow linha2 = new TableRow(this);
                TextView senha = new TextView(this);
                senha.setText("Senha: ");
                linha2.addView(senha);

                EditText tsenha = new EditText(this);
                tsenha.setTransformationMethod(new PasswordTransformationMethod());
                linha2.addView(tsenha);

                //linha 3
                TableRow linha3 = new TableRow(this);
                linha3.setGravity(Gravity.RIGHT);
                Button ok = new Button(this);
                ok.setText(" Login ");
                linha3.addView(ok);

                // adiciona linhas
                tableLayout.addView(linha1);
                tableLayout.addView(linha2);
                tableLayout.addView(linha3);

                setContentView(tableLayout);
            }

        }
    }
}
