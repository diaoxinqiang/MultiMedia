package com.example.qaz60_000.playaudiotest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;


public class MainActivity extends Activity implements View.OnClickListener{
    private Button play;
    private Button pause;
    private Button stop;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        initMediaPlayer();

    }
    private void initMediaPlayer()
    {
        File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
        Boolean isFile = file.exists();
        Log.i("filePath",isFile.toString()+file.getPath().toString());
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.i("io", "设置播放源失败:"+file.getPath().toString());
        }
    }
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.play:
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }


    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(mediaPlayer != null)
        {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
}
