package com.example.thepranami.antitheft;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OutgoingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, MusicActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }else if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_DISCONNECTED)){
            MusicActivity.mediaPlayer.stop();

        }
        else
        {
            Toast.makeText(context, "Power disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}
