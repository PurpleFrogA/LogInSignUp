package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button signupbt;
    private EditText email,password,confirmpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectComponents();
    }

    private void connectComponents() {
        signupbt = findViewById(R.id.btSignUpMain);
        email = findViewById(R.id.etEmailMain);
        password = findViewById(R.id.etPasswordMain);
        confirmpassword = findViewById(R.id.etPassword2Main);
    }

    public void signup(View view) {
        String emailstr, passwordstr, confirmpasswordstr;
        emailstr = email.getText().toString();
        passwordstr = password.getText().toString();
        confirmpasswordstr = confirmpassword.getText().toString();

        if (emailstr.trim().isEmpty() || passwordstr.trim().isEmpty() || confirmpasswordstr.trim().isEmpty()) {
            Toast.makeText(this, "you didnt entered all fields", Toast.LENGTH_LONG).show();
            return;
        }

        if (!isEmailValid(emailstr)) {
            Toast.makeText(this, "Email is incorrect", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isPasswordValid(passwordstr)) {
            Toast.makeText(this, "password should be written in another way", Toast.LENGTH_LONG).show();
            return;
        }
        if (!passwordstr.equals(confirmpasswordstr)){
            Toast.makeText(this, "passwords are not identical", Toast.LENGTH_SHORT).show();
        }
        // TODO: sign up to system

    }

    private boolean isPasswordValid(String passwordstr) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(passwordstr);

        return matcher.matches();
    }

    public boolean isEmailValid(String st)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(st);
        return matcher.matches();
    }
}