package com.ffisherr.csp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ffisherr.csp.retrofit.ElementsFounded;
import com.ffisherr.csp.retrofit.controllers.FindTechController;
import com.ffisherr.csp.users.User;

import java.util.ArrayList;
import java.util.List;

public class BosTech extends AppCompatActivity implements ElementsFounded {

    ArrayAdapter<String> adapter;
    List<String> userNames;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bos_tech);
        Log.e("", "Зашли куда нужно");
        listView = findViewById(R.id.list_tech_users);
        FindTechController controller = new FindTechController();
        controller.start(this);
    }

    @Override
    public void TechUsersFounded(List<User> users) {
        Log.e("", "Данные получены");
        List<String> userNamesLocal = new ArrayList<>();
        for (User u : users) {
            userNamesLocal.add(u.getSurName() + " " + u.getFirstName());
            System.out.println(u.getSurName() + " " + u.getFirstName());
        }
        userNames = userNamesLocal;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userNamesLocal);
        listView.setAdapter(adapter);
    }
}
