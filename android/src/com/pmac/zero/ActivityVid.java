package com.pmac.zero;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityVid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vid);
        video();
    }

    private void video() {
        String filename = "android.resource://" + getPackageName() + "/" + R.raw.proj;
        VideoView vv = findViewById(R.id.videoView);
        vv.setVideoURI(Uri.parse(filename));
        MediaController mediaC = new MediaController(this);
        mediaC.setAnchorView(vv);
        vv.setMediaController(mediaC);
        vv.start();
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ActivityVid.this.finish();
            }
        });
    }
}
