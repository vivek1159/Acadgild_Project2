package com.example.vivek.collegemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.Session;

public class ForgotPassword extends AppCompatActivity {

    EditText email, mobileNumber;
    String genPass;
    LoginDB loginDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginDB = new LoginDB(getApplicationContext());
        email = (EditText) findViewById(R.id.ForgotPassword_EmailAddress);
        mobileNumber = (EditText) findViewById(R.id.ForgotPassword_MobileNumber);
    }

    public void onClickProceed(View v) {

        final String Email = email.getText().toString();
        String MobileNumber = mobileNumber.getText().toString();

        if (loginDB.checkIfUserExists(Email,MobileNumber)) {
            final GMailSender autoMail = new GMailSender();
            String password = generatePassword();

            loginDB.updateUser(Email,password,"Y");

            final String Subject = "Reset Password";
            final String Body = "This is an auto-generated email. Your new password is " + password + ". Please change it immediately after login.";

            Boolean success = false;

            try {
                success = autoMail.sendMail(Subject, Body, "rsaic2000@gmail.com", Email);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (success) {
                Toast.makeText(ForgotPassword.this, "Email sent successfully!!", Toast.LENGTH_LONG).show();
                Intent login = new Intent(ForgotPassword.this,Login.class);
                startActivity(login);
            } else {
                Toast.makeText(ForgotPassword.this, "Some Problem occured!!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(ForgotPassword.this, "Email ID/Mobile Number not valid", Toast.LENGTH_LONG).show();
        }
    }

    private String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int min = 0;
        int max = 35;
        int generator;
        genPass = "";
        for (int i = 1; i <= 8; i++) {
            generator = (int) (min + (max - min) * (Math.random()));
            genPass += chars.charAt(generator);
        }
        return genPass;
    }
}
