package com.ffisherr.csp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ffisherr.csp.retrofit.Controller;
import com.ffisherr.csp.retrofit.controllers.FindUserController;
import com.ffisherr.csp.users.User;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Enter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acnivity_enter);

        Button Reg = (Button)findViewById(R.id.Enter_Button);
        Reg.setOnClickListener(v -> {
            try {
                EditText editText = findViewById(R.id.edit_user);
                String uLogin = editText.getText().toString();
                editText = findViewById(R.id.edit_password);
                String uPassword = editText.getText().toString();
                User user = new User();
                user.setEmail(uLogin);
                user.setPasssword(uPassword);
                FindUserController controller = new FindUserController();
                controller.start(this, uLogin, uPassword);
                finish();
            }catch (Exception e){
                Log.e("Start register activity", e.toString());
            }
        });


    }
}
