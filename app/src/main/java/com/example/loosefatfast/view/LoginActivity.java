package com.example.loosefatfast.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.loosefatfast.R;
import com.example.loosefatfast.presenter.LoginPresenter;
import com.google.firebase.FirebaseApp;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private LoginPresenter presenter;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);


        setContentView(R.layout.activity_login);
          // Interface for login view functionalities


        presenter = new LoginPresenter(this);

        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_login);
        emailEditText = findViewById(R.id.email);
       // Log.i("TAG",((TextView)findViewById(R.id.X)).getText().toString());

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            presenter.loginUser(email, password);
        });



    }

    public void onLoginSuccess() {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailure(String errorMessage) {
        Toast.makeText(this, "Login failed: " + errorMessage, Toast.LENGTH_LONG).show();
    }


}
