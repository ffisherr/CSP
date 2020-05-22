package com.ffisherr.csp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.ffisherr.csp.techno.ApplicationForm;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ffisherr.csp.Confnig.EXTRA_JOB_CREATOR_ID;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_DATE_WHEN_CLOSED;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_DATE_WHEN_CREATED;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_EQUIPMENT_ID;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_ID;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_PHONE_NUMBER;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_SOLVER_ID;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_STATUS;

public class TechUserActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private ApplicationForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_user);
        Intent intent = getIntent();
        form = new ApplicationForm();
        form.setId(intent.getIntExtra(EXTRA_JOB_ID, -1));
        form.setUserCreatorId(intent.getIntExtra(EXTRA_JOB_CREATOR_ID, -1));
        form.setUserSolverId(intent.getIntExtra(EXTRA_JOB_SOLVER_ID, -1));
        form.setWhenClosed((Date)intent.getSerializableExtra(EXTRA_JOB_DATE_WHEN_CLOSED));
        form.setWhenCreated((Date)intent.getSerializableExtra(EXTRA_JOB_DATE_WHEN_CREATED));
        form.setEquipmentId(intent.getIntExtra(EXTRA_JOB_EQUIPMENT_ID, -1));
        form.setStatus(intent.getIntExtra(EXTRA_JOB_STATUS, -1));
        form.setPhoneNumber(intent.getStringExtra(EXTRA_JOB_PHONE_NUMBER));

        CharSequence charSequence;

        textView = findViewById(R.id.numbeDoc);
        charSequence = Integer.toString(form.getId());
        textView.setText(charSequence);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        textView = findViewById(R.id.dateDoc);
        charSequence = formatter.format(form.getWhenCreated());
        textView.setText(charSequence);


        textView = findViewById(R.id.nameStatusDoc);
        System.out.println(form.getStatus());
        switch (form.getStatus()) {
            case 1:
                charSequence = "Ожидает подтверждения";
                break;
            case 2:
                charSequence = "Принята к рассмотрению";
                break;
            case 3:
                charSequence = "Отклонена";
                break;
            case 4:
                charSequence = "Принято положительное решение";
                break;
            default:
                charSequence = "Статус неизвестен";
                break;
        }
        textView.setText(charSequence);

        textView = findViewById(R.id.PhoneNumber);
        charSequence = form.getPhoneNumber();
        textView.setText(charSequence);

        button = findViewById(R.id.creatorIdTextButton);
        charSequence =  Integer.toString(form.getUserCreatorId());
        button.setText(charSequence);

        button = findViewById(R.id.equipmentIdTextButton);
        charSequence =  Integer.toString(form.getEquipmentId());
        button.setText(charSequence);

    }
}
