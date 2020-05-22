package com.ffisherr.csp;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ffisherr.csp.retrofit.body.RegisterBody;
import com.ffisherr.csp.retrofit.controllers.RegisterUserController;
import com.ffisherr.csp.users.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Registr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);
    }

    public void onClickRegister (View view) {
        RegisterBody user = new RegisterBody();

        EditText editText;

        editText = findViewById(R.id.edit_SecondName);
        user.surName = (editText.getText().toString());

        editText = findViewById(R.id.edit_FirstName);
        user.firstName = (editText.getText().toString());

        editText = findViewById(R.id.edit_surname);
        user.secondName = (editText.getText().toString());

        editText = findViewById(R.id.edit_login);
        user.email = (editText.getText().toString());

        editText = findViewById(R.id.edit_password_reg);
        user.password = (editText.getText().toString());

        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            editText = findViewById(R.id.DateBirthday);
            user.setBirthDate(formatter.parse(editText.getText().toString()));

            editText = findViewById(R.id.ContracDate_text);
            user. = (formatter.parse(editText.getText().toString()));
        } catch (ParseException e) {
            Toast.makeText(this, "Даты введены неверно не совпадают", Toast.LENGTH_LONG).show();
        }*/

        editText = findViewById(R.id.edit_repeatPassword);
        String recPassword = editText.getText().toString();
        if (recPassword.equals(user.password)) {
            RegisterUserController controller = new RegisterUserController();
            controller.start(this, user);
        } else {
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_LONG).show();
        }

    }
}