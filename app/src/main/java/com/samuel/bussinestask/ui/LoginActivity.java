package com.samuel.bussinestask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    Button btnForgotPass;

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
        btnForgotPass = findViewById(R.id.tvForgotPassword);

        btnLogin.setOnClickListener(v -> login());
        btnForgotPass.setOnClickListener(v -> initForgotPassword());

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
                if (response.isSuccessful() && response.body() != null) {

                    LoginResponse data = response.body();

                    SessionManager session = new SessionManager(LoginActivity.this);
                    session.saveSession(
                            data.getUserId(),
                            data.getToken(),
                            data.getRole()
                    );


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

    private void initForgotPassword() {
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(v -> {
            Log.d("LOGIN", "Click en recuperar contraseña");
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }





}