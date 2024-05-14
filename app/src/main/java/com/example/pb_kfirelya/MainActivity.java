package com.example.pb_kfirelya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button add,sub, restart;
    public TextView v1,v2,time;
    public EditText result;
    public ProgressBar pb;
    public CountDownTimer cdt;
    public int value1,value2;
    public Random rnd;
    boolean adding = false;
    boolean subbing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        sub = findViewById(R.id.subtract);
        time = findViewById(R.id.time);
        v1 = findViewById(R.id.value1);
        v2 = findViewById(R.id.value2);
        result = findViewById(R.id.result);
        pb = findViewById(R.id.progressBar);
        restart = findViewById(R.id.restart);
        restart.setVisibility(View.INVISIBLE);//restarting button set invisible until game finished
        restart.setEnabled(false);//restarting button not enabled

        rnd= new Random();
        value1= rnd.nextInt(11);
        value2 = rnd.nextInt(11);//randomizing 2 values
        int temp = value1;
        value1 = Math.max(value1,value2);
        value2 = Math.min(temp,value2);//bigger value is 1, smaller is 2
        v1.setText(String.valueOf(value1));
        v2.setText(String.valueOf(value2));

        pb.setProgress(120);
        time.setText("Time Left: "+16+" seconds");//creating progress bar with timer of 16 seconds
        cdt = new CountDownTimer(16000,1000) {
            int progress = 16;
            @Override
            public void onTick(long l) {
                progress--;
                time.setText("Time Left: "+progress+" seconds");//on each tick, one second passes and the progress bar is updated
                pb.setProgress((int)progress*100/(10));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this,"Time is up", Toast.LENGTH_LONG).show();
                v1.setVisibility(View.INVISIBLE);//when time is up, v1 and v2 textviews are set invisible
                v2.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.VISIBLE);//restart button set visible and enabled
                restart.setEnabled(true);
            }
        };
        cdt.start();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = Integer.parseInt(String.valueOf(result.getText()));
                if (String.valueOf(result.getText()) == null){//if sent empty
                    Toast.makeText(MainActivity.this, "please provide answer", Toast.LENGTH_SHORT).show();
                    restart.setVisibility(View.VISIBLE);
                    restart.setEnabled(true);
                }
                else if (adding){//if already answered this exercise
                    Toast.makeText(MainActivity.this, "Already answered", Toast.LENGTH_SHORT).show();
                }
                else{
                    if ((res) == value1+value2) {//if exercise is true
                        if (subbing) {//if both exercises were answered correctly
                            Toast.makeText(MainActivity.this, "You Won!", Toast.LENGTH_SHORT).show();
                            cdt.cancel();//progress bar and timer paused
                            v1.setVisibility(View.INVISIBLE);//when time is up, v1 and v2 textviews are set invisible
                            v2.setVisibility(View.INVISIBLE);
                            restart.setVisibility(View.VISIBLE);//restart button set visible and enabled
                            restart.setEnabled(true);
                        }
                        else {//if second exercise is still yet to be answered
                            adding = true;
                            Toast.makeText(MainActivity.this, "Answer Subbing Result", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{//if exercise isn't true
                        Toast.makeText(MainActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                        cdt.cancel();//progress bar and timer paused
                        v1.setVisibility(View.INVISIBLE);//when time is up, v1 and v2 textviews are set invisible
                        v2.setVisibility(View.INVISIBLE);
                        restart.setVisibility(View.VISIBLE);//restart button set visible and enabled
                        restart.setEnabled(true);
                    }
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = Integer.parseInt(String.valueOf(result.getText()));
                if (String.valueOf(result.getText()) == null){//if sent empty
                    Toast.makeText(MainActivity.this, "please provide answer", Toast.LENGTH_SHORT).show();
                    restart.setVisibility(View.VISIBLE);
                    restart.setEnabled(true);
                }
                else if (subbing){//if already answered this exercise
                    Toast.makeText(MainActivity.this, "Already answered", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (res == value1-value2) {
                        if (adding) {//if both exercises were answered correctly
                            Toast.makeText(MainActivity.this, "You Won!", Toast.LENGTH_SHORT).show();
                            cdt.cancel();//progress bar and timer paused
                            v1.setVisibility(View.INVISIBLE);//when time is up, v1 and v2 textviews are set invisible
                            v2.setVisibility(View.INVISIBLE);
                            restart.setVisibility(View.VISIBLE);//restart button set visible and enabled
                            restart.setEnabled(true);
                        }
                        else{//if second exercise is still yet to be answered
                            subbing = true;
                            Toast.makeText(MainActivity.this, "Answer Adding Result", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{//if exercise isn't true
                        Toast.makeText(MainActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                        cdt.cancel();//progress bar and timer paused
                        v1.setVisibility(View.INVISIBLE);//when time is up, v1 and v2 textviews are set invisible
                        v2.setVisibility(View.INVISIBLE);
                        restart.setVisibility(View.VISIBLE);//restart button set visible and enabled
                        restart.setEnabled(true);
                    }
                }
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });
    }
    public void restart(){
        restart.setVisibility(View.INVISIBLE);//restarting button set invisible until game finished
        restart.setEnabled(false);//restarting button not enabled
        v1.setVisibility(View.VISIBLE);
        v2.setVisibility(View.VISIBLE);
        value1 = rnd.nextInt(11);//randomizing 2 values
        value2 = rnd.nextInt(11);
        int temp = value1;
        value1 = Math.max(value1,value2);//bigger value is 1, smaller is 2
        value2 = Math.min(temp,value2);
        v1.setText(String.valueOf(value1));
        v2.setText(String.valueOf(value2));
        pb.setProgress(120);//creating progress bar with timer of 16 seconds
        adding = false;
        subbing = false;
        time.setText("Time Left: "+16+" seconds");
        cdt = new CountDownTimer(16000,1000) {
            int progress = 16;
            @Override
            public void onTick(long l) {
                progress--;
                time.setText("Time Left: "+progress+" seconds");//on each tick, one second passes and the progress bar is updated
                pb.setProgress((int)progress*100/(10));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this,"Time is up", Toast.LENGTH_LONG).show();
                v1.setVisibility(View.INVISIBLE);//when time is up, v1 and v2 textviews are set invisible
                v2.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.VISIBLE);//restart button set visible and enabled
                restart.setEnabled(true);
            }
        };
        cdt.start();
    }
}