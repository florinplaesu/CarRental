package com.example.carrental;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AccountFragment extends Fragment {

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mSignInButton;
    private FirebaseAuth mAuth;
    private TextView textView;


    public AccountFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);


        mAuth = FirebaseAuth.getInstance();
        mEmailField = view.findViewById(R.id.email_field);
        mPasswordField = view.findViewById(R.id.password_field);
        textView = view.findViewById(R.id.register_here);
        mSignInButton = view.findViewById(R.id.sign_in_button);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Register.class);
                startActivity(intent);
            }
        });


        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    // to add condition if user is admin or not, different privileges
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getActivity(), "Sign in successful", Toast.LENGTH_SHORT).show();
                                    Fragment newFragment = new AdminFragment();
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frameLayout, newFragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(getActivity(), "Sign in failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            ;
        });
        return view;
    }
}