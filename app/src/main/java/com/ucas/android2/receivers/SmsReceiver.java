package com.ucas.android2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("MyReceiver", "onReceive");
        final Bundle bundle = intent.getExtras();
        String senderNumber = "", messege = "";
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    senderNumber = currentMessage.getDisplayOriginatingAddress();
                    messege = currentMessage.getDisplayMessageBody();
                    Log.d("senderNumber", senderNumber + "");
                    Log.d("messege", messege + "");
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }
}
