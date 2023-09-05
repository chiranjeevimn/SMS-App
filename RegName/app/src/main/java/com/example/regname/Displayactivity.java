package com.example.regname;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Displayactivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView tna= (TextView) findViewById(R.id.name1);
        TextView tus= (TextView) findViewById(R.id.usn1);
        TextView tp= (TextView) findViewById(R.id.dep1);
        Intent i=getIntent();
        String s1=i.getStringExtra(MainActivity.Efn);
        String s12=i.getStringExtra(MainActivity.Eus);
        String s3=i.getStringExtra(MainActivity.Edp);

        tna.setText(s1);
        tus.setText(s12);
        tp.setText(s3);
    }
}
