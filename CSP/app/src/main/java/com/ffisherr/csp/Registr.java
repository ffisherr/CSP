package com.ffisherr.csp;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        // TODO Добавить проверку равенства введенных паролей
        // TODO Если не равны всплывающее окно

        editText = findViewById(R.id.edit_password_reg);
        user.password = (editText.getText().toString());

        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            editText = findViewById(R.id.DateBirthday);
            user.setBirthDate(formatter.parse(editText.getText().toString()));

            editText = findViewById(R.id.ContracDate_text);
            user.setEndOfContract(formatter.parse(editText.getText().toString()));
        } catch (ParseException e) {
            // TODO Высплывающее окно при неправильном вводе дат
        }*/

        RegisterUserController controller = new RegisterUserController();
        controller.start(this, user);

    }
}
