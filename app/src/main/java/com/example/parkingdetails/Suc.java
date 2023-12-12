package com.example.parkingdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Suc extends AppCompatActivity {

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suc);
        String n=getIntent().getStringExtra("pname");
        String it=getIntent().getStringExtra("intime");
        String ou=getIntent().getStringExtra("outtime");
        String money=getIntent().getStringExtra("money");
        Log.e("suc--------------------------------------------------------------------", it);
        Log.e("suc--------------------------------------------------------------------", ou);
        Log.e("suc--------------------------------------------------------------------", money);
        Log.e("suc--------------------------------------------------------------------", n);
     //   @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView pn=(TextView) findViewById(R.id.textView);
     /*   TextView itt=(TextView) findViewById(R.id.textView2);
        TextView ouu=(TextView) findViewById(R.id.textView3);
        TextView m=(TextView) findViewById(R.id.textView4);
        pn.setText(n);
        itt.setText(it);
        ouu.setText(ou);
        m.setText(money);*/
    }
}