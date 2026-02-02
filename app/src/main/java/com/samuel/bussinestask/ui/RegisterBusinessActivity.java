package com.samuel.bussinestask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.samuel.bussinestask.R;

public class RegisterBusinessActivity extends AppCompatActivity {

    private EditText etNit, etCompanyName, etAddress, etWeb;
    private Spinner spServiceType;
    private Button btnRegisterBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business);

        initViews();
        setupListeners();
    }

    private void initViews() {
        etNit = findViewById(R.id.etNit);
        etCompanyName = findViewById(R.id.etCompanyName);
        etAddress = findViewById(R.id.etAddress);
        etWeb = findViewById(R.id.etWeb);
        spServiceType = findViewById(R.id.spServiceType);
        btnRegisterBusiness = findViewById(R.id.btnRegisterBusiness);
    }

    private void setupListeners() {
        btnRegisterBusiness.setOnClickListener(v -> validarFormulario());
    }

    private void validarFormulario() {
        String nit = etNit.getText().toString().trim();
        String companyName = etCompanyName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        if (nit.isEmpty() || companyName.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Completa los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        irABienvenida();
    }

    private void irABienvenida() {
        Toast.makeText(this, "Empresa registrada correctamente", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
