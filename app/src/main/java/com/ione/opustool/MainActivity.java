package com.ione.opustool;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnTouchListener{
    Button btn_recorder,btn_player;
    PlayerUtils mPlayer;
    PlayImpl mPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int framsize = OpusJni.getInstance().OpusgetFrameSize();
        TextView tv_1 = (TextView)this.findViewById(R.id.tv_1);
        tv_1.setText("framsize:"+framsize);
        btn_recorder = (Button)this.findViewById(R.id.btn_recorder);
        btn_player = (Button)this.findViewById(R.id.btn_player);
        btn_recorder.setOnTouchListener(this);
        mPlay = new PlayImpl();
        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //MedioPlay播放
//                    mPlayer.setUrlPrepare(AudioFileUtils.getDecodeWavFilePath());
//                    mPlayer.play();
                    //用AudioTrac播放，声音较大，可以播放MODE_STREAM流，边播边取
                    mPlay.play();
                }catch (Exception e){

                }
            }
        });

        mPlayer = new PlayerUtils();
        OpusJni.getInstance().Opusopen(8);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OpusJni.getInstance().Opusclose();
        mPlayer.stop();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        AudioRecordUtils utils = AudioRecordUtils.getInstance();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPlayer.stop();
                utils.startRecordAndFile();
                break;
            case  MotionEvent.ACTION_UP:
                utils.stopRecordAndFile();
                break;
        }
        return false;
    }
}
