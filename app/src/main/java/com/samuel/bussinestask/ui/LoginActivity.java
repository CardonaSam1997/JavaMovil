package com.samuel.bussinestask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.samuel.bussinestask.R;
import com.samuel.bussinestask.api.ApiClient;
import com.samuel.bussinestask.api.AuthService;
import com.samuel.bussinestask.model.LoginRequest;
import com.samuel.bussinestask.model.LoginResponse;
import com.samuel.bussinestask.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SessionManager session = new SessionManager(this);
        if (session.isLoggedIn()) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        LoginRequest request = new LoginRequest(
                etEmail.getText().toString(),
                etPassword.getText().toString()
        );

        AuthService service = ApiClient.getClient().create(AuthService.class);

        service.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    SessionManager session = new SessionManager(LoginActivity.this);
                    session.saveToken(response.body().getToken());

                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
