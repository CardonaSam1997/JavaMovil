package com.samuel.bussinestask.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.samuel.bussinestask.R;
import com.samuel.bussinestask.util.SessionManager;

public class SelectRoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);

        SessionManager session = new SessionManager(this);

        findViewById(R.id.cardProfessional).setOnClickListener(v -> {
            session.saveRole("PROFESSIONAL");
            startActivity(new Intent(this, RegisterProfessionalActivity.class));
        });

        findViewById(R.id.cardBusiness).setOnClickListener(v -> {
            session.saveRole("BUSINESS");
            startActivity(new Intent(this, RegisterBusinessActivity.class));
        });
    }
}