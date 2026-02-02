package com.samuel.bussinestask.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private TextView tvConfirmation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.etEmailRecovery);
        Button btnEmailRecovery = findViewById(R.id.btnSendRecovery);

        btnEmailRecovery.setOnClickListener(v -> forgotPassword());

        tvConfirmation = findViewById(R.id.tvConfirmation);


    }

    public void forgotPassword() {

        String email = etEmail.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("El correo es obligatorio");
            return;
        }

        AuthService authService = ApiClient
                .getClientScalars()
                .create(AuthService.class);

        ForgotPasswordRequest request =
                new ForgotPasswordRequest(email);

        authService.forgotPassword(request)
                .enqueue(new Callback<String>() {

                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            tvConfirmation.setVisibility(View.VISIBLE);
                            etEmail.setEnabled(false);

                            Toast.makeText(
                                    ForgotPasswordActivity.this,
                                    "Si el correo existe, se enviará un enlace de recuperación",
                                    Toast.LENGTH_LONG
                            ).show();

                            new Handler(Looper.getMainLooper()).postDelayed(
                                    ForgotPasswordActivity.this::finish,
                                    5000
                            );

                        }
                        else {
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
