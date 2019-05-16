package com.example.thepranami.antitheft;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidServiceStartOnBoot extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Android Service", Toast.LENGTH_SHORT).show();

        //String Mobile_no = "9899355762";

        //String Text = "hi..";
        //String NetworkOperator = telephonyManager.getSimOperatorName();
        //String SimSerial_No = telephonyManager.getSimSerialNumber();

        Thread thread = new Thread() {
            public void run() {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
                    //SharedPreferences.Editor editor = sharedPreferences.edit();
                    String SIM_SERIAL = sharedPreferences.getString("SIM_SERIAL", "");
                    String NUM1 = sharedPreferences.getString("SIM_ONE", "");
                    String NUM2 = sharedPreferences.getString("SIM_TWO", "");
                    String nos[] = {NUM1, NUM2};
                    TelephonyManager telephonyManager;
                    telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                    //String nos[] = {"8081448062", "9899355762"};
                    if (ActivityCompat.checkSelfPermission(AndroidServiceStartOnBoot.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }

                    String SimSerial_No = telephonyManager.getSimSerialNumber();
                    String NetworkOperator = telephonyManager.getSimOperatorName();
                        //String Temp = "8991101706136998921f";
                        SmsManager smsManager = SmsManager.getDefault();
                        if (!SimSerial_No.equals(SIM_SERIAL)){

                            for (int i = 0; i < nos.length; i++) {
                                smsManager.sendTextMessage(nos[i], null, "Sim_Serial_No:" + SimSerial_No+ "\nOperator Name:"+ NetworkOperator, null, null);

                                sleep(5000);
                            }
                        }
                    }
                    catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
            };
            thread.start();

    }
}
