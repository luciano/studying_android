package com.example.luciano.testesantigo.TextView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.luciano.testes.R;

public class TextView1 extends Activity {

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);

        setContentView(R.layout.activity_text_view1);

        final TextView TVtext = (TextView) findViewById(R.id.TVtext1);

        TVtext.setText("\nsetText usando Java dinamicamente!");

    }
}