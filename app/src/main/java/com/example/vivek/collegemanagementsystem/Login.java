package com.example.vivek.collegemanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class Login extends AppCompatActivity {

    EditText Email, Password;
    Button LoginButton;
    TextView ForgotPassword;
    CheckBox CheckBox;

    SharedPreferences loginPreferences;
    LoginDB loginDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginDB = new LoginDB(getApplicationContext());

        Email = (EditText) findViewById(R.id.loginScreen_Email);
        Password = (EditText) findViewById(R.id.loginScreen_Password);
        LoginButton = (Button) findViewById(R.id.loginScreen_loginButton);
        ForgotPassword = (TextView) findViewById(R.id.loginScreen_forgotPassword);
        CheckBox = (CheckBox) findViewById(R.id.loginScreen_CheckBox);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Email.setText("");
        Password.setText("");

        loginPreferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);
        if (loginPreferences != null) {
            String email = loginPreferences.getString("Email", "");
            String password = loginPreferences.getString("Password", "");

            String user_type = loginDB.getUserType(email, password);
            Intent login;
            switch (user_type) {
                case "A":
                    login = new Intent(Login.this, AdminHome.class);
                    login.putExtra("Email",email);
                    startActivity(login);
                    break;

                case "T":
                    login = new Intent(Login.this, ProfessorHome.class);
                    login.putExtra("Email",email);
                    startActivity(login);
                    break;

                case "P":
                    login = new Intent(Login.this, AdminHome.class);
                    login.putExtra("Email",email);
                    startActivity(login);
                    break;

                case "S":
                    login = new Intent(Login.this, StudentHome.class);
                    login.putExtra("Email",email);
                    startActivity(login);
                    break;
            }
        }
    }

    public void onClickLoginButton(View v) throws SQLException {
        String emailInput = Email.getText().toString();
        String passwordInput = Password.getText().toString();

        if (emailInput.length() > 0 && passwordInput.length() > 0) {
            if (loginDB.ifNewUser(emailInput,passwordInput).equals("Y")) {
                Intent registration = new Intent(Login.this, Registration.class);
                registration.putExtra("EMAIL_ADDRESS", emailInput);
                startActivity(registration);
            } else {
                String user_type = loginDB.getUserType(emailInput, passwordInput);
                if (CheckBox.isChecked()) {
                    loginPreferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginPreferences.edit();
                    editor.putString("Email", emailInput);
                    editor.putString("Password", passwordInput);
                    editor.apply();
                }
                Intent login;
                switch (user_type) {
                    case "A":
                        login = new Intent(Login.this, AdminHome.class);
                        login.putExtra("Email",emailInput);
                        startActivity(login);
                        break;

                    case "T":
                        login = new Intent(Login.this, ProfessorHome.class);
                        login.putExtra("Email",emailInput);
                        startActivity(login);
                        break;

                    case "P":
                        login = new Intent(Login.this, AdminHome.class);
                        login.putExtra("Email",emailInput);
                        startActivity(login);
                        break;

                    case "S":
                        login = new Intent(Login.this, StudentHome.class);
                        login.putExtra("Email",emailInput);
                        startActivity(login);
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "Email ID/Password is not valid. Please get in touch with the school to register.", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        } else {
            Toast.makeText(Login.this, "Email/Password cannot be blank", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickForgotPassword(View v) {
        Intent forgotPassword = new Intent(Login.this, ForgotPassword.class);
        startActivity(forgotPassword);
    }
}