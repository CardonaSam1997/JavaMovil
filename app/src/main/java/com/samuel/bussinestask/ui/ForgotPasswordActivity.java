package com.samuel.bussinestask.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.samuel.bussinestask.R;
import com.samuel.bussinestask.api.ApiClient;
import com.samuel.bussinestask.api.AuthService;
import com.samuel.bussinestask.model.ForgotPasswordRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.etEmailRecovery);
    }

    public void forgotPassword() {

        String email = etEmail.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("El correo es obligatorio");
            return;
        }

        AuthService authService = ApiClient
                .getClient()
                .create(AuthService.class);

        ForgotPasswordRequest request =
                new ForgotPasswordRequest(email);

        authService.forgotPassword(request)
                .enqueue(new Callback<String>() {

                    @Override
                    public void onResponse(Call<String> call,
                                           Response<String> response) {

                        if (response.isSuccessful()) {
                            Toast.makeText(
                                    ForgotPasswordActivity.this,
                                    response.body(),
                                    Toast.LENGTH_LONG
                            ).show();
                        } else {
                            Toast.makeText(
                                    ForgotPasswordActivity.this,
                                    "Error al procesar la solicitud",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(
                                ForgotPasswordActivity.this,
                                "Error de conexión",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
    }
}
