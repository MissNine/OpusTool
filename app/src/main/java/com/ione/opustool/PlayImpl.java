package com.ione.opustool;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Description:播放实现
 * Author:qingxia
 * Created:2017/8/21 11:23
 * Version:
 */
public class PlayImpl implements IPlay {
    protected static int FREQUENCY = 44100;
    private static int CHANNEL = AudioFormat.CHANNEL_OUT_STEREO;
    private static int ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    //文件存放位置
    private final String FILEPATH = AudioFileUtils.getDecodePcmFilePath();
    private AudioTrack mAudioTrack;

    @Override
    public void open(int musicLength) {
        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, FREQUENCY,
                CHANNEL,
                ENCODING,
                musicLength * 2,
                AudioTrack.MODE_STREAM);
    }

    @Override
    public void play() {
        File file = new File(FILEPATH);
        int musiclength = (int) (file.length() / 2);
        short[] music = new short[musiclength * 2];
        try {
            InputStream inputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

            int i = 0;
            while (dataInputStream.available() > 0) {
                music[i] = dataInputStream.readShort();
                i++;
            }
            dataInputStream.close();

            open(musiclength);
            mAudioTrack.play();
            mAudioTrack.write(music, 0, musiclength);
            mAudioTrack.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mAudioTrack.stop();
    }
}
