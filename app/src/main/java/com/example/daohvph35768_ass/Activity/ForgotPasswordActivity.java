package com.example.daohvph35768_ass.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daohvph35768_ass.DAO.UserDAO;
import com.example.daohvph35768_ass.DTO.UserDTO;
import com.example.daohvph35768_ass.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText username, email;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
    //ánh xạ
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iusername = username.getText().toString().trim();
                String iemail = email.getText().toString().trim();

                try {
                    //check if username is empty
                    if (iusername.isEmpty()) {
                        username.setError("Username is required!");
                        username.requestFocus();
                        return;
                    }

                    //check if email is empty
                    if (iemail.isEmpty()) {
                        email.setError("Email is required!");
                        email.requestFocus();
                        return;
                    }

                    //check if email is valid
                    if (!Patterns.EMAIL_ADDRESS.matcher(iemail).matches()) {
                        email.setError("Please provide a valid email!");
                        email.requestFocus();
                        return;
                    }

                    //check username và email đúng ko
                    UserDAO userDAO = new UserDAO(ForgotPasswordActivity.this);

                    if (userDAO.checkUsernameAndEmail(iusername, iemail)){

                        UserDTO userDTO = userDAO.checkUsernameandEmailAfter(iusername,iemail);
                        if(userDTO != null) {
                            int id = userDTO.getId();
                            String uname = userDTO.getUsername();
                            String emaill = userDTO.getEmail();
                            String password = userDTO.getPassword();
                            String flname = userDTO.getFullname();

                            Toast.makeText(ForgotPasswordActivity.this, id+uname+emaill + password+flname, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ForgotPasswordActivity.this,NewPasswordActivity.class);

                            String idString = String.valueOf(id);
                            intent.putExtra("idDTO", idString);
                            intent.putExtra("userDTO", userDTO.getUsername());
                            intent.putExtra("fullnameDTO", userDTO.getFullname());
                            intent.putExtra("emailDTO", userDTO.getEmail());
                            intent.putExtra("passwordDTO", userDTO.getPassword());

                            startActivity(intent);
                            finish();
                        } else {
                            // handle the case where userDTO is null
                            Toast.makeText(ForgotPasswordActivity.this, "UserDTO is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //show error
                        Toast.makeText(ForgotPasswordActivity.this, "Incorrect username or email!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}