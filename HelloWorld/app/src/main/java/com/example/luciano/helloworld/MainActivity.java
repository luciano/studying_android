package com.example.luciano.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Teste", "Hello World");
        Log.e("Teste", "Hello World");
        Log.d("Teste", "Hello World");
        Log.v("Teste", "Hello World");
        Log.w("Teste", "Hello World");

    }
}
