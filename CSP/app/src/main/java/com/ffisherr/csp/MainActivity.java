package com.ffisherr.csp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ffisherr.csp.retrofit.Controller;
import com.ffisherr.csp.users.User;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    User me;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(Confnig.PREFERENCE_NAME, Context.MODE_PRIVATE);
        int myId = sp.getInt(Confnig.PREFERENCE_ID,-1);
        if (myId != -1) {
            Toast.makeText(this, "Known user", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Unknown user", Toast.LENGTH_LONG).show();
        }
        Controller controller = new Controller();
        controller.start(0);

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



    }
}
