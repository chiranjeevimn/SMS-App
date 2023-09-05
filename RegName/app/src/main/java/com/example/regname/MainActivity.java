package com.example.regname;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String Efn="com.example.rename.Efn";
    public static final String Eus="com.example.rename.Eus";
    public static final String Edp="com.example.rename.Edp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisActivity();
            }
        });
    }

    public void DisActivity() {
        EditText et1=(EditText) findViewById(R.id.m_name);
        EditText et2=(EditText) findViewById(R.id.usn);
        EditText et3=(EditText) findViewById(R.id.m_dep);

        String fn=et1.getText().toString();
        String usn=et2.getText().toString();
        String dpt1=et3.getText().toString();

        Intent i= new Intent(this,Displayactivity.class);
        i.putExtra(Efn,fn);
        i.putExtra(Eus,usn);
        i.putExtra(Edp,dpt1);
        startActivity(i);
    }
}