package com.jin.curious;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jin.curious.databinding.ActivityVidioBinding;
import com.remotemonster.sdk.RemonCall;

public class VidioActivity extends AppCompatActivity {
    private ActivityVidioBinding binding;
    private RemonCall remonCall = new RemonCall();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_vidio);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_vidio);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        String channelId;

        remonCall = RemonCall.builder()
                .context(this)
                .serviceId("id")
                .key("key")
                .videoCodec("VP8")
                .videoWidth(640)
                .videoHeight(480)
                .localView(binding.localView)
                .remoteView(binding.remoteView)
                .build();
        channelId = getIntent().getStringExtra("channelId");
        Toast.makeText(VidioActivity.this, "channelId :" + channelId.toString(), Toast.LENGTH_SHORT).show();
        remonCall.connect(channelId);
        //remonCall.getOnClose();

    }

    @Override
    protected void onDestroy() {
        remonCall.close();
        super.onDestroy();
    }
}