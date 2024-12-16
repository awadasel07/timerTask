package com.example.timer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private TextView time ;
private Button start , stop,rest;
private int m=0;
private int s=0;
Thread thread;
private boolean click =true;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        time=findViewById(R.id.time);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        rest=findViewById(R.id.reset);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }
    public void reset (){
        m=0;
        s=0;
        time.setText(m+":"+s);
    }
    public void start(){
        if(click)
        {
            click=false;
            thread=new Thread(()->{
                try {
                    while (true){
                        s++;
                        if (s==60) {
                          s=0;
                          m++;
                        }
                        time.setText(m+":"+s);
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();

        }
    }

    public void stop(){
        thread.interrupt();
        click=true;
    }
}