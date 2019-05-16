package com.example.thepranami.antitheft;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserData extends AppCompatActivity {
    SharedPreferences preferences;
    TextView nameTextView, idTextView, passwordTextView, simSerialTextView, num1TextView, num2TextView;
    //Button editButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        preferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);

        nameTextView=(TextView)findViewById(R.id.user_name);
        idTextView=(TextView)findViewById(R.id.user_id);
        passwordTextView=(TextView)findViewById(R.id.user_pass);
        simSerialTextView=(TextView)findViewById(R.id.simSerial_id);
        num1TextView=(TextView)findViewById(R.id.num1_id);
        num2TextView=(TextView)findViewById(R.id.num2_id);
       // editButton=(Button)findViewById(R.id.edit_id);
       // logoutButton=(Button)findViewById(R.id.logout_id);

        String NAME = preferences.getString("NAME", "");
        String ID = preferences.getString("EMAIL", "");
        String PASSWORD = preferences.getString("PASSWORD", "");
        String SIMSERIAL = preferences.getString("SIM_SERIAL", "");
        String NUM1 = preferences.getString("SIM_ONE", "");
        String NUM2 = preferences.getString("SIM_TWO", "");

        nameTextView.setText(NAME);
        idTextView.setText(ID);
        passwordTextView.setText(PASSWORD);
        simSerialTextView.setText(SIMSERIAL);
        num1TextView.setText(NUM1);
        num2TextView.setText(NUM2);

      /*  editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(UserData.this, SecurityForm.class);
                startActivity(editIntent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent loginIntent = new Intent(UserData.this, LoginActivity.class);
                //startActivity(loginIntent);
                finish();
            }
        }); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_userdata, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.edit_menu_id:
                Intent editIntent = new Intent(UserData.this, SecurityForm.class);
                startActivity(editIntent);
                return true;
            case R.id.change_pass_menu_id:
                Intent changePassIntent = new Intent(UserData.this, ChangePassword.class);
                startActivity(changePassIntent);
                return true;
            case R.id.logout_menu_id:
                AlertDialog.Builder a_builder = new AlertDialog.Builder(UserData.this);
                a_builder.setMessage("Logout!!!").setCancelable(false)
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent loginIntent = new Intent(UserData.this, LoginActivity.class);
                                startActivity(loginIntent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = a_builder.create();
                alertDialog.show();
                return true;

            case R.id.exit_menu_id:
                AlertDialog.Builder ab_builder = new AlertDialog.Builder(UserData.this);
                ab_builder.setMessage("Do you want to exit ?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // finish();
                                finishAffinity();
                                //return true;
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog ab_alertDialog = ab_builder.create();
                ab_alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent loginIntent = new Intent(UserData.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
