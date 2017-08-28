package com.example.unknown.findmyinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Created by UNKNOWN on 7/24/2016.
 */
public class DialohHandler extends Handler {

    ProgressDialog p;
    Context context;
    public DialohHandler(Context context){
        this.context = context;
        p = new ProgressDialog(context);
        p.setMessage("Loading...");
        p.show();
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        p.dismiss();
    }
}
