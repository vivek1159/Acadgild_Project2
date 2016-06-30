package com.example.vivek.collegemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    TextView Email;
    EditText Password, ConfirmPassword;
    Button ProceedButton;
    LoginDB loginDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String reg = getIntent().getStringExtra("EMAIL_ADDRESS");

        loginDB = new LoginDB(getApplicationContext());

        Email = (TextView) findViewById(R.id.Registration_EmailAddress);
        Password = (EditText) findViewById(R.id.Registration_Password);
        ConfirmPassword = (EditText) findViewById(R.id.Registration_ConfirmPassword);
        ProceedButton = (Button) findViewById(R.id.Registration_ProceedButton);

        Email.setText(reg);
    }

    public void onClickProceedButton(View v) {
        String password = Password.getText().toString();
        String confirmPassword = ConfirmPassword.getText().toString();

        if (password.equals(confirmPassword)) {
            loginDB.updateUser(Email.getText().toString(), password, "N");
            Intent login = new Intent(Registration.this, Login.class);
            Toast.makeText(Registration.this, "Please login with your new password!", Toast.LENGTH_LONG).show();
            startActivity(login);
        } else {
            Toast.makeText(Registration.this, "Passwords do not match", Toast.LENGTH_LONG).show();
        }
    }
}
