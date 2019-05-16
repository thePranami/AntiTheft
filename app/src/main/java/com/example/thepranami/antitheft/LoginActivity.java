package com.example.thepranami.antitheft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEmailEditText, loginPasswordEditText;
    private Button loginButton;
    TextView forgotTextView, aboutUsTextView;
    ActionBar actionBar;
    FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        actionBar = getSupportActionBar();
        actionBar.setSubtitle("Login Panel");
        fabButton=(FloatingActionButton)findViewById(R.id.fab_id);
        loginEmailEditText=(EditText)findViewById(R.id.loginEmailEditText_id);
        loginPasswordEditText=(EditText)findViewById(R.id.loginPasswordEditText_id);
        loginButton=(Button)findViewById(R.id.loginButton_id);
        //changePassButton=(Button)findViewById(R.id.change_pass_id);
        forgotTextView=(TextView)findViewById(R.id.forgotPasswordTextView_id);
        aboutUsTextView=(TextView)findViewById(R.id.aboutUsTextView_id);

      /*  loginEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    hideKeyboard(v);
                }
            }
        });   */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SharedPreferences sharedPreferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
               String SaveId = sharedPreferences.getString("EMAIL", "");
               String SavePass = sharedPreferences.getString("PASSWORD", "");
                String Id = loginEmailEditText.getText().toString();
                String Pass = loginPasswordEditText.getText().toString();

                if (Id.isEmpty() || Pass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill all field values", Toast.LENGTH_SHORT).show();
                }
               else if (SaveId.equals(Id) && SavePass.equals(Pass)){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, UserData.class);
                    startActivity(intent);
                    loginEmailEditText.setText("");
                    loginPasswordEditText.setText("");
                }
                else{
                    Toast.makeText(LoginActivity.this, "You entered wrong Id or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent forgetPassIntent = new Intent(LoginActivity.this, ForgetPassword.class);
              startActivity(forgetPassIntent);
            }
        });
        aboutUsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sorry... No Data Found", Toast.LENGTH_SHORT).show();
            }
        });
        // fabButton
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog;
                dialog = new AlertDialog.Builder(LoginActivity.this);
                dialog.setMessage("Do you want to exit?").setCancelable(false)
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alt = dialog.create();
                alt.show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent i = new Intent(LoginActivity.this, MainActivity.class);
       //startActivity(i);
       finishAffinity();
    }
   /* public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }*/
}

