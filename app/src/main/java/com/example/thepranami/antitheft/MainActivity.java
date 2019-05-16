package com.example.thepranami.antitheft;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                    Intent intent = new Intent(MainActivity.this, RegistrationForm.class);
                    startActivity(intent);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
  // @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
  // @Override
   /* public void onBackPressed() {
        super.onBackPressed();
        int backpress = 0;
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back Again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            finishAffinity();
            //this.finish();
        }
    } */
}
