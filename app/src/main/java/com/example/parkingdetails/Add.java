package com.example.parkingdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button btn=(Button) findViewById(R.id.next);
        Button btn1=(Button) findViewById(R.id.next1);
        TextView n=(TextView) findViewById(R.id.name1);
        TextView p=(TextView) findViewById(R.id.ph);
        TextView p1=(TextView) findViewById(R.id.ph1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dh=new DatabaseHelper(Add.this);
                String name = n.getText().toString();  // Use getText() and toString() to get the text as a String
                String ph = p.getText().toString();
                dh.insrec(name,ph);
                Intent intent = new Intent(Add.this, MainActivity.class);
                intent.putExtra("STRING_KEY",ph);
                startActivity(intent);
            }
        });
    btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String ph = p1.getText().toString();
            Intent intent = new Intent(Add.this, MainActivity.class);
            intent.putExtra("STRING_KEY",ph);
            startActivity(intent);
        }
    });
    }
}