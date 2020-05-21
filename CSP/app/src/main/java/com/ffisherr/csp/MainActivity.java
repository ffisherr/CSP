package com.ffisherr.csp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button In = findViewById(R.id.AutoButton);
        In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, Enter.class);
                    startActivity(intent);finish();
                }catch (Exception e){
                    Log.e("Start enter activity", e.toString());
                }
            }
        });

        Button Reg = (Button)findViewById(R.id.RegistrButton);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent1 = new Intent(MainActivity.this, Registr.class);
                    startActivity(intent1);finish();
                }catch (Exception e){
                    Log.e("Start register activity", e.toString());
                }
            }
        });
        Button Reg1 = (Button)findViewById(R.id.RegistrButton);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent1 = new Intent(MainActivity.this, UserScreenActivity.class);
                    startActivity(intent1);finish();
                }catch (Exception e){
                    Log.e("Start register activity", e.toString());
                }
            }
        });

    }
}
