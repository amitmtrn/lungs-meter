package com.breathe.lungsmeter;

import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;

import java.io.IOException;

public class CheckLungs extends AppCompatActivity {

    private MediaRecorder recorder = new MediaRecorder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Button b=(Button)findViewById(R.id.button);

        b.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_UP:
//                        stopRecord();
//                        return true;
//                    case MotionEvent.ACTION_DOWN:
//                        try {
//                            startRecord();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        return true;
//                }
//
//                return false;
                System.out.println("touch");
                return false;
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_lungs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check_lungs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startRecord() throws IOException {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile("/home/amit/x.3gp");
        recorder.prepare();
        recorder.start();
    }

    private void stopRecord() {
        recorder.stop();
    }
}
