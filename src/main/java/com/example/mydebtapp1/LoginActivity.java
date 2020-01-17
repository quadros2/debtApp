package com.example.mydebtapp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.junk.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth fireBaseAuth;
    private EditText emailEntry;
    private EditText passwordEntry;
    private String email;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fireBaseAuth = FirebaseAuth.getInstance();
        TextView promptEmail = findViewById(R.id.promptEmail);
        promptEmail.setVisibility(View.VISIBLE);
        TextView promptPassword = findViewById(R.id.promptPassword);
        promptPassword.setVisibility(View.VISIBLE);
        emailEntry = findViewById(R.id.emailEntry);
        emailEntry.setVisibility(View.VISIBLE);
        passwordEntry = findViewById(R.id.passwordEntry);
        passwordEntry.setVisibility(View.VISIBLE);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setVisibility(View.VISIBLE);

        if (fireBaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        loginButton.setOnClickListener(V -> {
            email = emailEntry.getText().toString();
            password = passwordEntry.getText().toString();
            logInFlow(email, password);
        });
        Button signinButton = findViewById(R.id.signInButton);
        signinButton.setVisibility(View.VISIBLE);
        signinButton.setOnClickListener(V -> {
            signUpFlow();
        });
    }

    public void logInFlow(String email, String password) {
        fireBaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Sign in +", "signInWithEmail:success");
                            FirebaseUser user = fireBaseAuth.getCurrentUser();
                            String username = user.getEmail();
                            updateUI(username);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Sign in -", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Log In failed.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    //create new ui for sign up page!!!!!
    public void signUpFlow() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void updateUI(String user) {
        if (user == null) {
            return;
        }
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
