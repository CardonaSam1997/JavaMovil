package com.samuel.bussinestask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.samuel.bussinestask.R;

public class RegisterProfessionalActivity extends AppCompatActivity {

    private EditText etDocument, etName, etLastName, etAddress,
            etPhone, etAcademic, etBirthDate, etExperience, etDescription;
    private Spinner spGender, spServiceType;
    private Button btnRegisterProfessional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_professional);

        initViews();
        setupListeners();
    }

    private void initViews() {
        etDocument = findViewById(R.id.etDocument);
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        etAcademic = findViewById(R.id.etAcademic);
        etBirthDate = findViewById(R.id.etBirthDate);
        etExperience = findViewById(R.id.etExperience);
        etDescription = findViewById(R.id.etDescription);

        spGender = findViewById(R.id.spGender);
        spServiceType = findViewById(R.id.spServiceType);

        btnRegisterProfessional = findViewById(R.id.btnRegisterProfessional);
    }

    private void setupListeners() {
        btnRegisterProfessional.setOnClickListener(v -> validarFormulario());
    }

    private void validarFormulario() {
        String document = etDocument.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();

        if (document.isEmpty() || name.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(this, "Completa los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        irABienvenida();
    }

    private void irABienvenida() {
        Toast.makeText(this, "Registro profesional completado", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
