package com.example.daohvph35768_ass.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daohvph35768_ass.DAO.UserDAO;
import com.example.daohvph35768_ass.Preferences.UserPreference;
import com.example.daohvph35768_ass.R;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView signupText, forgotpassword;
    Button loginButton;
    private UserPreference userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check if user is logged in
        userPreferences = new UserPreference(this);

        if (userPreferences.isLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.signupText);
        forgotpassword = findViewById(R.id.forgotpassword);

        //set event click for signupText
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity. this,SignupActivity.class));
                finish();
            }
        });

        //set event click for ForgotPassword
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                finish();
            }
        });

        //set event click for btnLogin
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nusername = username.getText().toString().trim();
                String npassword = password.getText().toString().trim();

                try {
                    // Check if username is empty
                    if (nusername.isEmpty()) {
                        username.setError("Username cannot be empty");
                        username.requestFocus();
                        return;
                    }

                    // Check if username exists
                    UserDAO userDAO = new UserDAO(LoginActivity.this);
                    if (!userDAO.checkUsername(nusername)) {
                        username.setError("Username does not exist");
                        username.requestFocus();
                        return;
                    }

                    // Check if password is empty
                    if (npassword.isEmpty()) {
                        password.setError("Password cannot be empty");
                        password.requestFocus();
                        return;
                    }

//                    //check password
                    if (!userDAO.checkAccount(nusername, npassword)) {
                        password.setError("Incorrect password!");
                        password.requestFocus();
                        return;
                    }

                    //go to MainActivity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}