package com.fraint.eco.Firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.fraint.eco.Firebase.CLNotificacion;
import com.fraint.eco.P_InterfazUsuario;
import com.fraint.eco.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
    private static final String TAG="ECOMESSAGINGSERVICE";
    private static final String KEY_DESCUENTO="key_descuento";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String DeviceToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN", DeviceToken);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From:"+remoteMessage.getFrom());

        CLNotificacion notificacion = new CLNotificacion();
        notificacion.setID(remoteMessage.getFrom());
        notificacion.setTitulo(remoteMessage.getNotification().getTitle());
        notificacion.setDescripcion(remoteMessage.getNotification().getBody());

        notificacion.setDescuento(remoteMessage.getData().get(KEY_DESCUENTO));
        showNotification(notificacion);
    }

    private void showNotification(CLNotificacion notificacion)
    {
        Intent intent=new Intent(this, P_InterfazUsuario.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent= PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Object context = null;
        NotificationCompat.Builder notificationBLD= new NotificationCompat.Builder(this, "channelId");
        notificationBLD.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notificacion.getTitulo())
                .setContentText(notificacion.getDescripcion())
                .setAutoCancel(true)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(10000, notificationBLD.build());
    }
}
