package com.example.luciano.testesantigo.Button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luciano.testes.R;

public class Button1 extends Activity {
    @Override
    public void onCreate(Bundle b) {

        super.onCreate(b);

        setContentView(R.layout.activity_button1);

        final Button button2 = (Button) findViewById(R.id.Button2);

        button2.setText("Java Button");

        button2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(button2.getContext(), "Botao usando onCLickListener em Java!", Toast.LENGTH_LONG).show();
            }
        });

        final Button button3 = (Button) findViewById(R.id.Button3Enviar);

        button3.setText("Enviar");

        button3.setOnClickListener(button3Click);
    }

    //disparado pelo XML pelo atributo onclick do button1
    public void fazAlgo(View v) {

        Toast.makeText(this, "Botao usando onCLick em XML!", Toast.LENGTH_LONG).show();
    }

    private View.OnClickListener button3Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final TextView TVbutton3 = (TextView) findViewById(R.id.TVbutton3);
            final EditText ETbutton3 = (EditText) findViewById(R.id.ETbutton3);

            TVbutton3.setText("");

            String s = ETbutton3.getText().toString();

            if(!s.isEmpty()) {
                TVbutton3.setText("Obrigado " + s);

                ETbutton3.setText("");
            }
            else
                Toast.makeText(v.getContext(),"Antes de pressionar o botao escreva seu nome!",Toast.LENGTH_SHORT).show();
        }
    };
}
