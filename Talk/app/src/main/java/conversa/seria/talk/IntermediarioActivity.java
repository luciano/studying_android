package conversa.seria.talk;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class IntermediarioActivity extends Arquivo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        ArrayList<String> strings = new ArrayList<>();

        String[] l = TextUtils.split(leitura("intermediario"), "\n");

        Log.i(MainActivity.CATEGORIA, "Leitura: " + Arrays.toString(l));

        strings.addAll(Arrays.asList(l));

        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);

        for(String assunto: strings) {

            TextView textView = new TextView(this);
            textView.setText(assunto);
            layout2.addView(textView);

            View view = new View(this);
            view.setBackgroundColor(0xaf503f22);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
            layout2.addView(view);
        }
*/
        setContentView(R.layout.activity_mostruario);
    }

    public void addConteudo(View v) {
        startActivity(new Intent(this, AddActivity.class).putExtra("tabela", "intermediario"));
    }

}
