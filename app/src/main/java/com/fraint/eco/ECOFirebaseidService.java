package com.fraint.eco;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class ECOFirebaseidService extends FirebaseInstanceIdService {
    private static final String TAG = "ECOinstanceidservice";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String DeviceToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN", DeviceToken);
    }
}
