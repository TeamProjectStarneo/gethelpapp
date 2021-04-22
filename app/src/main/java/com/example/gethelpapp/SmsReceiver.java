package com.example.gethelpapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import com.example.gethelpapp.db.data.SpecialistDao;
import com.example.gethelpapp.db.data.UserDataBase;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    private ReceiverCallback callback;
    MessageActivity ma;
    String msgBody;
    String msg_from;



    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            String format = bundle.getString("format");
            SmsMessage[] msgs = null;
            msg_from = null;
            if(bundle!=null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i],format);
                        msg_from = msgs[i].getOriginatingAddress();
                        msgBody = msgs[i].getMessageBody();
                        Toast.makeText(context,"From" + msg_from + " " + msgBody,Toast.LENGTH_SHORT).show();
                        Log.i("Text Message",msgBody);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            //callback.doStuff(msgBody);
            Intent intent1 = new Intent("com.example.gethelpapp",null);
            intent.putExtra("msgbody",msgBody);
            context.sendBroadcast(intent1);

            msg_from="0879712652";
        }
    }

    public void onReceieve(Context context, Intent intent)
    {
        Log.i("test","test");
    }
}

