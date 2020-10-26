package com.example.alarmnac;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class MyBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        Notification notificacao = new Notification.Builder(context)
                .setContentTitle("Alarme Ligado")
                .setContentText("Você selecionou o Alarme")
                .setSmallIcon(R.mipmap.ic_launcher).build();

        NotificationManager adm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificacao.flags|= Notification.FLAG_AUTO_CANCEL;
        adm.notify(0,notificacao);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
    }
}
