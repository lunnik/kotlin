package com.odn.kotlinretrofi.periodictasks;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by EDGAR ARANA on 08/11/2017.
 */

public class MyTestService extends IntentService {

    public MyTestService() {
        super("MyTestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Do the task here
        Log.e("MyTestService", "Service running");
    }
}