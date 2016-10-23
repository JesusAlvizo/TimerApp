package com.jesusalvizo.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.jesusalvizo.timer.R.raw.airhorn;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pauseBtn = (Button) findViewById(R.id.pauseBtn);
        timerSeekBar = (SeekBar)findViewById(R.id.controlSeekBar);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        controlBtn = (Button)findViewById(R.id.controlBtn);
        resBtn = (Button) findViewById(R.id.resbtn);

        timerTextView.setEnabled(false);

        pauseBtn.setEnabled(false);
        pauseBtn.setVisibility(View.INVISIBLE);
        resBtn.setEnabled(false);
        resBtn.setVisibility(View.INVISIBLE);

        timerSeekBar.setMax(5940);
        timerSeekBar.setProgress(60);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    Button controlBtn;
    Button pauseBtn;
    Button resBtn;

    TextView timerTextView;

    SeekBar timerSeekBar;

    Boolean counterIsOn = false;

    CountDownTimer Timer;

    public void controlTimer(View view) {

        if (!counterIsOn) {

            counterIsOn = true;
            timerSeekBar.setEnabled(false);
            controlBtn.setText("Stop");
            pauseBtn.setEnabled(true);
            pauseBtn.setVisibility(View.VISIBLE);
            resBtn.setEnabled(true);
            resBtn.setVisibility(View.VISIBLE);

            Timer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("00:00");
                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), airhorn);
                    mplayer.start();
                }

            }.start();

        } else {

            resume();
            pause();
            resetTimer();

        }
    }

    public void updateTimer(int secondsLeft) {
        int minutes = (int) secondsLeft / 60;
        int seconds = (int) secondsLeft % 60;

        String secondsString = Integer.toString(seconds);
        if (secondsString.length() < 2) {
            secondsString = "0" + secondsString;
        }

        String minutesString = Integer.toString(minutes);
        if (minutesString.length() < 2) {
            minutesString = "0" + minutesString;
        }

        timerTextView.setText(minutesString + ":" + secondsString);
    }

    public void resetTimer() {
        timerTextView.setText("01:00");
        timerSeekBar.setProgress(60);
        Timer.cancel();
        controlBtn.setText("Start");
        timerSeekBar.setEnabled(true);
        counterIsOn = false;
        pauseBtn.setEnabled(false);
        pauseBtn.setVisibility(View.INVISIBLE);
        resBtn.setEnabled(false);
        resBtn.setVisibility(View.INVISIBLE);
    }

    //aqui es donde quiero hacer que el tiemnpo se ponga pausa
    public void pause() {

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //counterIsOn = false;
                //Timer.wait(long);
            }
        });

    }

    //aqui quiero hacer que el tiempo se resuma en donde se quedo cuando esta en pausa
    public void resume() {

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //counterIsOn = true;
                //Timer.go();
            }
        });

    }

}
