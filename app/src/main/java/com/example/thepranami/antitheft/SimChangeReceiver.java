package com.example.thepranami.antitheft;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SimChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_NEW_OUTGOING_CALL)){
            Toast.makeText(context, "Action fire", Toast.LENGTH_SHORT).show();
        }

//        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)
//                || intent.getAction().equalsIgnoreCase(Intent.ACTION_CONFIGURATION_CHANGED)
//                || intent.getAction().equalsIgnoreCase(Intent.ACTION_LOCKED_BOOT_COMPLETED)
//                || intent.getAction().equalsIgnoreCase(Intent.ACTION_REBOOT)
//                || intent.getAction().equalsIgnoreCase(Intent.ACTION_UNINSTALL_PACKAGE)
//        || intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED)) {
//            Toast.makeText(context, "Action fire ", Toast.LENGTH_SHORT).show();
//
//            Intent serviceIntent = new Intent(context, AndroidServiceStartOnBoot.class);
//            context.startService(serviceIntent);
//        }
        Toast.makeText(context, "Boot Completed", Toast.LENGTH_SHORT).show();
    }
}
