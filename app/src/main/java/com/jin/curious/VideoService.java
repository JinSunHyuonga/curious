package com.jin.curious;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jin.curious.databinding.ActivityVidioBinding;
import com.remotemonster.sdk.RemonCall;

public class VideoService extends Service {

    private ActivityVidioBinding binding;
    private RemonCall remonCall = new RemonCall();
    String shared = "file";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //binding = DataBindingUtil.setContentView(this,R.layout.activity_vidio);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        String channelId;
        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        channelId = sharedPreferences.getString("loginId","");

        Toast.makeText(getApplicationContext(), "serivce :"+channelId, 0).show();

        channelId = "curious"+channelId.substring(0,channelId.indexOf("@"));

        remonCall = RemonCall.builder()
                .context(this)
                .serviceId("id")
                .key("key")
                .videoCodec("VP8")
                .videoWidth(640)
                .videoHeight(480)
                //.localView(binding.localView)
                //.remoteView(binding.remoteView)
                .build();
        remonCall.connect(channelId);
        //remonCall.getOnClose();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        remonCall.close();
        super.onDestroy();
    }
}
