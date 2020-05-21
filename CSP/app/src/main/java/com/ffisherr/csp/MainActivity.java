package com.ffisherr.csp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

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

        } else {
            Toast.makeText(this, "Unknown user", Toast.LENGTH_LONG).show();
        }
    }
}
