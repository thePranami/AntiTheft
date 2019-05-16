package com.example.thepranami.antitheft;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    EditText oldPassEditText, newPassEditText, conPassEditText;
    Button okChangePassButton;
    SharedPreferences preferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPassEditText=(EditText)findViewById(R.id.old_pass_id);
        newPassEditText=(EditText)findViewById(R.id.new_pass_id);
        conPassEditText=(EditText)findViewById(R.id.con_pass_id);
        okChangePassButton=(Button)findViewById(R.id.ok_change_id);

        okChangePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String SAVE_PASSWORD = preferences.getString("PASSWORD", "");
                String OLD_PASSWORD = oldPassEditText.getText().toString();
                String NEW_PASSWORD = newPassEditText.getText().toString();
                String CON_PASSWORD = conPassEditText.getText().toString();

                if (SAVE_PASSWORD.equals(OLD_PASSWORD) && NEW_PASSWORD.equals(CON_PASSWORD)){
                    editor.putString("PASSWORD", NEW_PASSWORD);
                    editor.commit();
                    new ChangePassTask().execute();

                }
                else {
                    Toast.makeText(ChangePassword.this, "Enter correct password and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    class ChangePassTask extends AsyncTask<Void, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ChangePassword.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                //Do something...
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Toast.makeText(ChangePassword.this, "Your password has been changed", Toast.LENGTH_LONG).show();
            oldPassEditText.setText("");
            newPassEditText.setText("");
            conPassEditText.setText("");
            Intent loginIntent = new Intent(ChangePassword.this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

}
