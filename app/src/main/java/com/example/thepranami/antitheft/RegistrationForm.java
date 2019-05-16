package com.example.thepranami.antitheft;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationForm extends AppCompatActivity {
    EditText nameEditText, mobileEditText, emailEditText, passwordEditText, con_passwordEditText;
    Button registerButton;
    TextView loginTextView;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        actionBar = getSupportActionBar();
        actionBar.setSubtitle("Registration Form");
        nameEditText=(EditText)findViewById(R.id.nameEditText_id);
        mobileEditText=(EditText)findViewById(R.id.mobileEditText_id);
        emailEditText=(EditText)findViewById(R.id.emailEditText_id);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText_id);
        con_passwordEditText=(EditText)findViewById(R.id.con_passwordEditText_id);

        registerButton=(Button)findViewById(R.id.registerButton_id);
        loginTextView=(TextView)findViewById(R.id.loginTextView_id);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("REG_STATUS", false);
        if (check){
            Intent i = new Intent(RegistrationForm.this, LoginActivity.class );
            startActivity(i);
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = nameEditText.getText().toString();
                String Mobile = mobileEditText.getText().toString();
                String Email = emailEditText.getText().toString();
                String Password = passwordEditText.getText().toString();
                String ConPass = con_passwordEditText.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (Name.isEmpty() || Mobile.isEmpty() || Email.isEmpty() || Password.isEmpty() || ConPass.isEmpty() ){
                    Toast.makeText(RegistrationForm.this, "Please fill all field values.", Toast.LENGTH_SHORT).show();
                }
                else if (Password.equals(ConPass)){
                    editor.putString("NAME", Name);
                    editor.putString("MOBILE", Mobile);
                    editor.putString("EMAIL", Email);
                    editor.putString("PASSWORD", Password);
                    editor.putBoolean("REG_STATUS", true);
                    editor.commit();
                    Toast.makeText(RegistrationForm.this, "Thanks your data has been submitted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationForm.this, SecurityForm.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegistrationForm.this, "Password is not matched", Toast.LENGTH_SHORT).show();
                }

            }
        });
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegistrationForm.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
