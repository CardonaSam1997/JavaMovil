package com.samuel.bussinestask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.samuel.bussinestask.R;
import com.samuel.bussinestask.util.SessionManager;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView tvRole = findViewById(R.id.tvRole);
        Button btnLogout = findViewById(R.id.btnLogout);

        SessionManager session = new SessionManager(this);
        String role = session.getRole();

        if (role != null) {
            tvRole.setText("Rol: " + role);
        } else {
            tvRole.setText("Rol no definido");
        }


        btnLogout.setOnClickListener(v -> {
            session.logout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
