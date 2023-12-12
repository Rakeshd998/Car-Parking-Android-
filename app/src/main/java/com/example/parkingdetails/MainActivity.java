package com.example.parkingdetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.parkingdetails.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(binding.navView, navController);
        Button btn=(Button) findViewById(R.id.btn);
        TextView t=(TextView) findViewById(R.id.money);
        TextView intime=(TextView)findViewById(R.id.intime);
        TextView outtime=(TextView)findViewById(R.id.outtime);
        TextView name=(TextView)findViewById(R.id.pname);
        String ph = getIntent().getStringExtra("STRING_KEY");
        String n=name.getText().toString();
        String it=intime.getText().toString();
        String ou=outtime.getText().toString();
        String money= (String) t.getText();
        Log.e("Main--------------------------------------------------------------------", n);
        Log.e("Main--------------------------------------------------------------------", it);
        Log.e("Main--------------------------------------------------------------------", ou);
        Log.e("Main--------------------------------------------------------------------", money);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Card.class);
                intent.putExtra("pname",n);
                intent.putExtra("intime",it);
                intent.putExtra("outtime",ou);
                intent.putExtra("money",money);
                intent.putExtra("STRING_KEY",ph);
                startActivity(intent);
            }
        });
    }

}