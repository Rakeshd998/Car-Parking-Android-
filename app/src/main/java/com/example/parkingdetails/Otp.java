package com.example.parkingdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.telephony.SmsManager;
import java.util.Random;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Otp extends AppCompatActivity {

    private String expectedOTP; // Replace with your expected OTP
    private EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4;
    String n=new String();
    String it=new String();
    String ou=new String();
    String money=new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        n=getIntent().getStringExtra("pname");
        it=getIntent().getStringExtra("intime");
        ou=getIntent().getStringExtra("outtime");
        money=getIntent().getStringExtra("money");

        // Retrieve the phone number from the previous activity
        String ph = getIntent().getStringExtra("phoneNumber");
        TextView t = findViewById(R.id.tt1);
        t.setText("Waiting for OTP");

        // Generate a 4-digit OTP
        String otp = generateOTP();

        // Send the OTP to the user's phone number
        sendOTP(ph, otp);

        // Initialize EditText fields for OTP digits
        otpDigit1 = findViewById(R.id.otpDigit1);
        otpDigit2 = findViewById(R.id.otpDigit2);
        otpDigit3 = findViewById(R.id.otpDigit3);
        otpDigit4 = findViewById(R.id.otpDigit4);

        // Set up text change listeners for OTP digits
        setupOtpTextListeners();

        // Set the expected OTP (replace with your expected OTP)
        expectedOTP = Integer.toString(Integer.parseInt(otp));
    }

    // Generate a 4-digit OTP
    private String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }

    // Send the OTP via SMS
    private void sendOTP(String phoneNumber, String otp) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, "Your OTP is: " + otp, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Set up text change listeners for OTP digits
    private void setupOtpTextListeners() {
        otpDigit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    otpDigit2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    // Allow user to go back to the previous EditText (otpDigit1)
                    otpDigit1.requestFocus();
                }
            }
        });

        otpDigit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    otpDigit3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    otpDigit1.requestFocus();
                }
            }
        });

        otpDigit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    otpDigit4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    otpDigit2.requestFocus();
                }
            }
        });

        otpDigit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Compare the entered OTP with the expected OTP
                String enteredOTP = otpDigit1.getText().toString() +
                        otpDigit2.getText().toString() +
                        otpDigit3.getText().toString() +
                        otpDigit4.getText().toString();

                if (enteredOTP.equals(expectedOTP)) {
                    Log.e("otp--------------------------------------------------------------------", it);
                    Log.e("otp--------------------------------------------------------------------", ou);
                    Log.e("otp--------------------------------------------------------------------", money);
                    Log.e("otp--------------------------------------------------------------------", n);
                    Toast.makeText(Otp.this, "Suscessfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Otp.this, Suc.class);
                    intent.putExtra("pname",n);
                    intent.putExtra("intime",it);
                    intent.putExtra("outtime",ou);
                    intent.putExtra("money",money);
                    startActivity(intent);

                } else {
                    Toast.makeText(Otp.this, "UN Suscessfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    otpDigit3.requestFocus();
                }
            }
        });
    }
}
