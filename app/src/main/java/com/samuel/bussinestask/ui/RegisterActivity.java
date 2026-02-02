package com.samuel.bussinestask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.samuel.bussinestask.R;
import com.samuel.bussinestask.api.ApiClient;
import com.samuel.bussinestask.api.UserApi;
import com.samuel.bussinestask.model.UserCreateRequest;
import com.samuel.bussinestask.model.UserResponse;
import com.samuel.bussinestask.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etEmail, etPassword, etPasswordConfirm;
    Button btnRegister;
    TextView tvGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoLogin = findViewById(R.id.tvGoLogin);

        btnRegister.setOnClickListener(v -> validarFormulario());

        tvGoLogin.setOnClickListener(v -> {
            finish();
        });
    }

    private void validarFormulario() {
        String user = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();
        String confirm = etPasswordConfirm.getText().toString();

        if (user.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(confirm)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.length() < 8) {
            etPassword.setError("La contraseña debe tener al menos 8 caracteres");
            etPassword.requestFocus();
            return;
        }

        registrarUsuario();

    }

    private void registrarUsuario() {

        UserCreateRequest dto = new UserCreateRequest(
                etUsername.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                "ADMIN"
        );

        UserApi api = ApiClient.getClient().create(UserApi.class);

        api.register(dto).enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    UserResponse user = response.body();
                    continuarRegistro(user.getId());

                } else {
                    try {
                        String errorBody = response.errorBody().string();

                        if (errorBody.contains("email")) {
                            etEmail.setError("El correo ya está registrado");
                        } else if (errorBody.contains("usuario")) {
                            etUsername.setError("El nombre de usuario ya existe");
                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "Error al registrar",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(RegisterActivity.this,
                                "Error inesperado",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }


            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,
                        "Error de conexión",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void continuarRegistro(Integer userId) {
        SessionManager session = new SessionManager(this);
        session.saveUserId(userId);


        Intent intent = new Intent(this, SelectRoleActivity.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish();
    }

}
