package com.example.thepranami.antitheft;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import java.util.jar.Attributes;
import android.telephony.TelephonyManager;

public class SecurityForm extends AppCompatActivity {
    EditText simSerialEditText, simOneEditText, simTwoEditText;
    Button saveButton;
    TextView textView;
    TelephonyManager telephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_form);

        simSerialEditText=(EditText)findViewById(R.id.simOneSerial_id);
        simOneEditText=(EditText)findViewById(R.id.simOne_id);
        simTwoEditText=(EditText)findViewById(R.id.simTwo_id);
        textView = (TextView)findViewById(R.id.textView_id);
        saveButton=(Button)findViewById(R.id.save_id);
        telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(android.content.Context.TELEPHONY_SERVICE);

        String S_No = telephonyManager.getSimSerialNumber();
        textView.setText(S_No);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String SimSerialNo = simSerialEditText.getText().toString();
                String SimOne = simOneEditText.getText().toString();
                String SimTwo = simTwoEditText.getText().toString();
                android.content.SharedPreferences sharedPreferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
                android.content.SharedPreferences.Editor editor = sharedPreferences.edit();

                if (SimSerialNo.isEmpty() || SimOne.isEmpty() || SimTwo.isEmpty()){
                    Toast.makeText(SecurityForm.this, "Please fill all field values.", Toast.LENGTH_SHORT).show();
                }
                else {
                    editor.putString("SIM_SERIAL", SimSerialNo);
                    editor.putString("SIM_ONE", SimOne);
                    editor.putString("SIM_TWO", SimTwo);
                    editor.commit();
                    Toast.makeText(SecurityForm.this, "Thanks your data has been submitted successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SecurityForm.this, UserData.class);
                    startActivity(intent);
                }

            }
        });
    }
}
