package com.ffisherr.csp;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ffisherr.csp.retrofit.body.RegisterBody;
import com.ffisherr.csp.retrofit.controllers.RegisterUserController;
import com.ffisherr.csp.users.User;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

public class Registr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);
    }

    public void onClickRegister (View view) {
        RegisterBody User = new RegisterBody();

        EditText editText = findViewById(R.id.edit_password_reg);
        EditText repeatPassword = findViewById(R.id.edit_repeatPassword);

        TextView infText = findViewById(R.id.info);

        String p1 = editText.getText().toString();
        String p2 = repeatPassword.getText().toString();

        if (p1.equals(p2)) {

            editText = findViewById(R.id.edit_FirstName);
            User.firstName(editText.getText().toString());

            editText = findViewById(R.id.edit_surname);
            User.surName(editText.getText().toString());

            editText = findViewById(R.id.edit_SecondName);
            User.secondName(editText.getText().toString());

            User.setPassw_hash(p1);
            User.setRole_id(1);



            editText = findViewById(R.id.edit_login);
            User.setLogin(editText.getText().toString());

            if (!User.checkIfOk()) {
                Toast.makeText(this, "Не заполнены обязательные поля", Toast.LENGTH_LONG).show();
                return;
            }

            String result;
            TaskPostServer ts = new TaskPostServer();
            String url = ServerDescriptor.serverIpAdress + "/user_add";

            Gson g = new Gson();
            String user_data = g.toJson(User);
            ts.execute(url, user_data);

            try {
                result = ts.get();
                if (result.equals("[{'status':'connectionError'}]")){
                    Toast.makeText(this, "Нет интернет соединения", Toast.LENGTH_LONG).show();
                    return;
                }
                UserResponse ur = g.fromJson(result, UserResponse.class);
                System.out.println(result);
                if (ur.getStatus().equals(ServerDescriptor.SUCCESS)) {
                    infText.setText("");

                    Toast.makeText(this, "Вы зарегестрированы", Toast.LENGTH_LONG).show();

                    Intent mStartActivity = new Intent(Registr.this, MainActivity.class);
                    int mPendingIntentId = 123456;
                    PendingIntent mPendingIntent = PendingIntent.getActivity(Registr.this, mPendingIntentId, mStartActivity,
                            PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager mgr = (AlarmManager) Registr.this.getSystemService(Context.ALARM_SERVICE);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                    System.exit(0);

                } else if (ur.getStatus().equals(ServerDescriptor.LOGIN_ALREADY_EXISTS_ERROR)){
                    Toast.makeText(this, "Введенный логин уже используется", Toast.LENGTH_LONG).show();
                    //infText.setText("Введенный логин уже используется");
                } else if (ur.getStatus().equals(ServerDescriptor.INTERNET_ERROR)){
                    Toast.makeText(this, "Не удается получить доступ к серверу", Toast.LENGTH_LONG).show();
                    //infText.setText("Не удается получить доступ к серверу");
                } else {
                    Toast.makeText(this, "Не удается получить доступ к серверу", Toast.LENGTH_LONG).show();
                    //infText.setText("Не удается получить доступ к серверу");
                }
            } catch (InterruptedException e) {
                infText.setText("error");
                e.printStackTrace();
            } catch (ExecutionException e) {
                infText.setText("error");
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_LONG).show();
        }
    }


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
        controller.start(this, User);

}

