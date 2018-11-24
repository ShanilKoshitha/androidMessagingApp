package com.shanilkoshitha.flashchatnewfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        
        mAuth = FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)   {
        
        attemptLogin();

    }

    // Executed when Register button pressed
    public void registerNewUser(View v) {
        Intent intent = new Intent(this, com.shanilkoshitha.flashchatnewfirebase.RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    
    private void attemptLogin() {


        
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if((email.length() <1)|| (password.length() <1)){
            return;
        }
        Toast.makeText(this,"Login in progress",Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("FlashChat", "Sign in complete: "+ task.isSuccessful());

                if(!task.isSuccessful()){
                    Log.d("FlashChat", "Error occured signing in "+task.getException());
                    showErrorDiag("There was a problem signing in");
                }else{
                    Intent intent = new Intent(LoginActivity.this, MainChatActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });


    }

    
    private void showErrorDiag(String message){

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }

}
