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

public class SignupActivity extends AppCompatActivity {

    EditText username, fullname, password, againPassword, email;
    Button loginButton;
    TextView tvLogin;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //ánh xạ
        username = findViewById(R.id.username);
        fullname = findViewById(R.id.fullname);
        password = findViewById(R.id.password);
        againPassword = findViewById(R.id.againPassword);
        email = findViewById(R.id.email);
        loginButton = findViewById(R.id.loginButton);
        tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String musername = username.getText().toString().trim();
                String mfullname = fullname.getText().toString().trim();
                String mpassword = password.getText().toString().trim();
                String memail = email.getText().toString().trim();
                String magainpassword = againPassword.getText().toString().trim();
                // Kiểm tra rỗng
                try {
                    if (musername.isEmpty()) {
                        username.setError("Username is required");
                        return;
                    }

                    userDAO = new UserDAO(SignupActivity.this);
                    // Kiểm tra xem username đã tồn tại hay chưa
                    if (userDAO.checkUser(musername)) {
                        username.setError("Username already exists");
                        return;
                    }

                    if (!musername.matches("^[a-zA-Z0-9_]{6,20}$")) {
                        username.setError("Username is invalid");
                        return;
                    }

                    if (mfullname.isEmpty()) {
                        fullname.setError("Full name is required");
                        return;
                    }

                    if (mpassword.isEmpty()) {
                        password.setError("Password is required");
                        return;
                    }

                    if (magainpassword.isEmpty()) {
                        againPassword.setError("Please re-enter your password");
                        return;
                    }

                    if (!mpassword.equals(magainpassword)) {
                        againPassword.setError("Password does not match");
                        return;
                    }

                    if (password.length() < 0) {
                        password.setError("Password must be at least 8 characters!");
                    }

                    if (memail.isEmpty()) {
                        email.setError("Email is required");
                        return;
                    }

                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(memail).matches()) {
                        email.setError("Email is invalid");
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //create new user
                UserDTO user = new UserDTO(musername, mfullname, mpassword, memail);

                UserDAO userDAO = new UserDAO(SignupActivity.this);
                long result = userDAO.insert(user);

                if (result < 0) {
                    // Nếu việc chèn dữ liệu không thành công, hiển thị thông báo lỗi và dừng việc xử lý đăng ký
                    Toast.makeText(SignupActivity.this, "Registration failed" + result, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Nếu việc chèn dữ liệu thành công, hiển thị thông báo thành công và chuyển sang màn hình đăng nhập
                Toast.makeText(SignupActivity.this, "Registration succeeded", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}