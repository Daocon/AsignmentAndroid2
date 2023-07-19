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
import com.example.daohvph35768_ass.DTO.UserDTO;
import com.example.daohvph35768_ass.R;

public class NewPasswordActivity extends AppCompatActivity {
    Button loginButton;
    EditText newpassword, againpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        loginButton = findViewById(R.id.loginButton);
        newpassword = findViewById(R.id.newpassword);
        againpassword = findViewById(R.id.againpassword);


        Intent intent = getIntent();

        String idDTOString = intent.getStringExtra("idDTO");
        String username = intent.getStringExtra("userDTO");
        String fullname = intent.getStringExtra("fullnameDTO");
        String email = intent.getStringExtra("emailDTO");
        String password = intent.getStringExtra("passwordDTO");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass = newpassword.getText().toString().trim();
                String confirmpass = againpassword.getText().toString().trim();

                //check data

                //check newpass trống
                if(newpass.isEmpty()){
                    newpassword.setError("New password is required!");
                    newpassword.requestFocus();
                    return;
                }

                if (confirmpass.isEmpty()){
                    againpassword.setError("Confirm new password is required!");
                    againpassword.requestFocus();
                    return;
                }
                if (!newpass.equals(confirmpass)){
                    againpassword.setError("Confirm new password does not match!");
                    againpassword.requestFocus();
                    return;
                }

                UserDAO userDAO = new UserDAO(NewPasswordActivity.this);

                //check pass cũ giống pass mới ko
                if (userDAO.checkPassword(newpass)){
                    againpassword.setError("Please enter a different password!");
                    againpassword.requestFocus();
                    return;
                }
                //cập nhật pass ở csdl
                int idDTO = Integer.parseInt(idDTOString);
                UserDTO userDTO = new UserDTO(idDTO,username,fullname,newpass,email);

                //check update succ
                int result = userDAO.updateUser(userDTO);
                if (result > 0 ){
                    Toast.makeText(NewPasswordActivity.this, "Reset password succ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewPasswordActivity.this,LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(NewPasswordActivity.this, "Reset password faild" + result, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}