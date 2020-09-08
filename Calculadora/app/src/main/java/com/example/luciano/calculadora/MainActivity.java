package com.example.luciano.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ETvisor = (EditText) findViewById(R.id.ETvisor);

        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETvisor.setText("");
            }

        });

        Button buttonDividir = (Button) findViewById(R.id.buttonDividir);
        buttonDividir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button buttonMult = (Button) findViewById(R.id.buttonMult);
        buttonMult.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button buttonSeta = (Button) findViewById(R.id.buttonSeta);
        buttonSeta.setText("<-");
        buttonSeta.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();

                if(s.isEmpty())
                {
                    ETvisor.setText("");
                }
                else
                {
                    String aux = s.substring(0, s.length()-1);

                    Log.i("Dep", "S: " + s);
                    Log.i("Dep", "Aux: " + aux);

                    ETvisor.setText(aux);
                }

            }
        });

        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("7");
                ETvisor.setText(s);
            }
        });

        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("8");
                ETvisor.setText(s);
            }
        });

        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("9");
                ETvisor.setText(s);
            }
        });

        Button buttonMenos = (Button) findViewById(R.id.buttonMenos);
        buttonMenos.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("4");
                ETvisor.setText(s);
            }
        });

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("5");
                ETvisor.setText(s);
            }
        });

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("6");
                ETvisor.setText(s);
            }
        });

        Button buttonMais = (Button) findViewById(R.id.buttonMais);
        buttonMais.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("1");
                ETvisor.setText(s);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("2");
                ETvisor.setText(s);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("3");
                ETvisor.setText(s);
            }
        });

        Button buttonSinal = (Button) findViewById(R.id.buttonSinal);
        buttonSinal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button buttonPonto = (Button) findViewById(R.id.buttonPonto);
        buttonPonto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //apenas um ponto
                String s = ETvisor.getText().toString();
                s = s.concat(".");
                ETvisor.setText(s);
            }
        });

        Button button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ETvisor.getText().toString();
                s = s.concat("0");
                ETvisor.setText(s);
            }
        });

        Button buttonIgual = (Button) findViewById(R.id.buttonIgual);
        buttonIgual.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
//desafio transformar string em double e pegar corretamente os operadores
                //usar final Double valueOf(string)

                Double b = 0.0;
                Log.i("Dep", "Double: " + b);
                b = b.valueOf("1.23");
                Log.i("Dep", "Double: " + b);


            }
        });
    }
}
