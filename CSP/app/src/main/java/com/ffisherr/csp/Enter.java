package com.ffisherr.csp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class Enter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acnivity_enter);

        Button Reg = (Button)findViewById(R.id.Enter_Button);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent1 = new Intent(Enter.this, TechUserActivity.class);
                    startActivity(intent1);finish();
                }catch (Exception e){
                    Log.e("Start register activity", e.toString());
                }
            }
        });


    }
}
