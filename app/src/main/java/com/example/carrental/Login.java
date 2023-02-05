package com.example.carrental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
        private EditText mEmailField;
        private EditText mPasswordField;
        private Button mSignInButton;
        private FirebaseAuth mAuth;
        private TextView textView;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent mainIntent = new Intent(Login.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            mAuth = FirebaseAuth.getInstance();

            mEmailField = findViewById(R.id.email_field);
            mPasswordField = findViewById(R.id.password_field);
            mSignInButton = findViewById(R.id.sign_in_button);
            textView= findViewById(R.id.registerNow);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(getApplicationContext(), Register.class);
                    startActivity(intent);
                    finish();
                }
            });


            mSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });
        }

        private void signIn() {
            String email = mEmailField.getText().toString();
            String password = mPasswordField.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(Login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                                Intent mainIntent = new Intent(Login.this, AddLocationFragment.class);
                                startActivity(mainIntent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Login.this, "Sign in failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
}
