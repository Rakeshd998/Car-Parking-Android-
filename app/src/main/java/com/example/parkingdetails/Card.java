package com.example.parkingdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Card extends AppCompatActivity {
    RecyclerView recyclerView;
    List<CardItem> cardItems;


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        String ph= getIntent().getStringExtra("STRING_KEY");
        String n=getIntent().getStringExtra("pname");
        String it=getIntent().getStringExtra("intime");
        String ou=getIntent().getStringExtra("outtime");
        String money=getIntent().getStringExtra("money");
        Log.e("card--------------------------------------------------------------------", it);
        Log.e("card--------------------------------------------------------------------", ou);
        Log.e("card--------------------------------------------------------------------", money);
        Log.e("card--------------------------------------------------------------------", n);
        DatabaseHelper dh=new DatabaseHelper(Card.this);
        // Retrieve user's ID based on phone number
        int userId = dh.getUserIdByPhoneNumber(ph);

        // Check if a valid user ID is found
        if (userId != -1) {
            // Query card details based on the user ID
            List<CardItem> cardItems = dh.getCardDetailsByUserId(userId);

            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            // Initialize and set the adapter with the card details
            CardAdapter adapter = new CardAdapter(cardItems,n,it,ou,money);
            recyclerView.setAdapter(adapter);
            Button makePaymentButton = findViewById(R.id.makePaymentButton);
            CheckBox rememberMeCheckBox = findViewById(R.id.ch1);

            makePaymentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText cardNumberEditText = findViewById(R.id.e1);
                    EditText expDateEditText = findViewById(R.id.expDateEditText);
                    EditText cvvEditText = findViewById(R.id.cvvEditText);
                    EditText bankNameEditText = findViewById(R.id.e2);

                    String cardNumber = cardNumberEditText.getText().toString();
                    String expDate = expDateEditText.getText().toString();
                    String cvv = cvvEditText.getText().toString();
                    String bankName = bankNameEditText.getText().toString();

                    if (cardNumber.isEmpty() || expDate.isEmpty() || cvv.isEmpty() || bankName.isEmpty()) {
                        // Show an error message if any of the fields is empty
                        Toast.makeText(Card.this, "Please fill in all card details", Toast.LENGTH_SHORT).show();
                    } else {
                        // All card details are entered
                        if (rememberMeCheckBox.isChecked()) {
                            // Save card details to the database
                            // Insert your database code here
                            if (userId != -1) {
                                dh.insCard(userId, bankName, cardNumber, expDate);
                            }else {
                                Toast.makeText(Card.this, "User not exist", Toast.LENGTH_SHORT).show();
                            }
                            // Redirect to OTP.java page
                            Intent intent = new Intent(Card.this, Otp.class);
                            intent.putExtra("pname",n);
                            intent.putExtra("intime",it);
                            intent.putExtra("outtime",ou);
                            intent.putExtra("money",money);
                            intent.putExtra("phoneNumber",ph);
                            startActivity(intent);
                        } else {
                            // Don't save card details, directly go to OTP.java
                            Intent intent = new Intent(Card.this, Otp.class);
                            intent.putExtra("pname",n);
                            intent.putExtra("intime",it);
                            intent.putExtra("outtime",ou);
                            intent.putExtra("money",money);
                            intent.putExtra("phoneNumber",ph);
                            startActivity(intent);
                        }
                    }
                }
            });

        }
    }

}