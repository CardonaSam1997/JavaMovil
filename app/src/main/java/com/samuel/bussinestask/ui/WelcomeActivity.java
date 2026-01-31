package com.samuel.bussinestask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.samuel.bussinestask.R;
import com.samuel.bussinestask.util.SessionManager;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            SessionManager session = new SessionManager(this);
            session.logout();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}