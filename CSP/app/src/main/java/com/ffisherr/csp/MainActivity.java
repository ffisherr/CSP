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
import com.ffisherr.csp.retrofit.controllers.FindJobForTechController;
import com.ffisherr.csp.users.User;

import static com.ffisherr.csp.Confnig.PREFERENCE_FIRST_NAME;
import static com.ffisherr.csp.Confnig.PREFERENCE_ID;
import static com.ffisherr.csp.Confnig.PREFERENCE_ROLE_ID;
import static com.ffisherr.csp.Confnig.PREFERENCE_SECOND_NAME;
import static com.ffisherr.csp.Confnig.PREFERENCE_SUR_NAME;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    User me;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(Confnig.PREFERENCE_NAME, Context.MODE_PRIVATE);
/*
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(PREFERENCE_ID,      -1);
        editor.putInt(PREFERENCE_ROLE_ID, -1);
        editor.commit();*/


        int myId = sp.getInt(Confnig.PREFERENCE_ID,-1);
        int myRoleId = sp.getInt(Confnig.PREFERENCE_ROLE_ID, -1);
        if (myId != -1 && myRoleId != -1) {
            Toast.makeText(this, "Known user", Toast.LENGTH_LONG).show();
            switch (myRoleId) {
                case 0:
                    System.out.println("Chief user");
                    Intent intent2 = new Intent(MainActivity.this, UserBosActvivty.class);
                    startActivity(intent2);
                    break;
                case 1:
                    System.out.println("Tech support");
                    FindJobForTechController jobController = new FindJobForTechController();
                    jobController.start(this, myId);
                    break;
                case 2:
                    Intent intent = new Intent(MainActivity.this, UserScreenMenuActivity.class);
                    startActivity(intent);
                    System.out.println("Usual user");
                    break;
                case 3:
                    Intent intent1 = new Intent(MainActivity.this, BosTech.class);
                    startActivity(intent1);
                    System.out.println("Tech TeamLead");
                    break;
                default:
                    Toast.makeText(this, "Неизвестный пользователь", Toast.LENGTH_LONG).show();
                    System.out.println("Unknown role");
                    break;
            }
            finish();
        }

        Button In = findViewById(R.id.AutoButton);
        In.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, Enter.class);
                startActivity(intent);finish();
            }catch (Exception e){
                Log.e("Start enter activity", e.toString());
            }
        });

        Button Reg = (Button)findViewById(R.id.RegistrButton);
        Reg.setOnClickListener(v -> {
            try {
                Log.e("Start register activity", "");
                Intent intent1 = new Intent(MainActivity.this, Registr.class);
                startActivity(intent1);finish();
            }catch (Exception e){
                Log.e("Start register activity", e.toString());
            }
        });
    }
}
