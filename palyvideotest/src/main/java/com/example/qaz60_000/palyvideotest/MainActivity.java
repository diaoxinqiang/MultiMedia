package com.example.qaz60_000.palyvideotest;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;


public class MainActivity extends Activity implements View.OnClickListener {
    private VideoView videoView;
    private Button play;
    private Button pause;
    private Button replay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoview);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        replay = (Button) findViewById(R.id.replay);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);

        initVideoView();
    }

    private void initVideoView()
    {
        File file = new File(Environment.getExternalStorageDirectory(),"video.mp4");
        Boolean isExist = file.exists();
        Log.i("file", isExist+file.getPath());
        videoView.setVideoPath(file.getPath());

    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.play:
                if(!videoView.isPlaying())
                {
                  videoView.start();
                }
                break;
            case R.id.pause:
                if(videoView.isPlaying())
                {
                    videoView.pause();
                }
                break;
            case R.id.replay:
                if(videoView.isPlaying())
                {
                    videoView.resume();
                }
                break;
            default:
                break;

        }
    }
    protected void onDestroy()
    {
        super.onDestroy();
        if(videoView!=null)
        {
            videoView.suspend();
        }
    }
}
