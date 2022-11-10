package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button signupbt;
    private Button loginbt;
    private TextView dele;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectComponents();
    }

    private void connectComponents() {
        signupbt = findViewById(R.id.bSignUpf);
        loginbt = findViewById(R.id.loginbt);
        dele = findViewById(R.id.textView);
    }

    public void signupFragment(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new SignUPFragment());
        ft.commit();
        dele.setText(" ");
    }

    public void LoginFragment(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new LoginFragment());
        ft.commit();
        dele.setText(" ");
    }
}