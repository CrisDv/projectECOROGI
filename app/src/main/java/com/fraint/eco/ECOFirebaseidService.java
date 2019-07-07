package com.fraint.eco;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.messaging.FirebaseMessagingService;


public class ECOFirebaseidService extends FirebaseMessagingService {
    private static final String TAG = "ECOinstanceidservice";

   /* @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String DeviceToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN", DeviceToken);
    }*/

   @Override
    public void onNewToken(String token)
   {
       super.onNewToken(token);
       Log.d("New_Token", token);
   }
}
