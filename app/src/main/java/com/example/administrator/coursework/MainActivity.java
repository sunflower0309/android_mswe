package com.example.administrator.coursework;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button submit;
    private Button tofill;
    private CheckBox box1;
    private CheckBox box2;
    private CheckBox box3;
    private CheckBox box4;
    private CheckBox box5;
    private CheckBox box6;
    private TextView countnum;
    private Chronometer chronometer1;
    private Chronometer chronometer2;
    private long recordtime=0;
    private int count=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        chronometer1.setBase(SystemClock.elapsedRealtime());
        chronometer1.start();
        chronometer2.setBase(SystemClock.elapsedRealtime());
        chronometer2.start();
        countnum.setText(String.valueOf(count));
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(count>1){
                    count-=1;
                    countnum.setText(String.valueOf(count));
                    if(box1.isChecked()&&box2.isChecked()&&box3.isChecked()&&box4.isChecked()){
                        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                        intent.putExtra("result","you passed");
                        intent.putExtra("fails",3-count);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"you are wrong, "+count+" times left",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if(box1.isChecked()&&box2.isChecked()&&box3.isChecked()&&box4.isChecked()){
                        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                        intent.putExtra("result","you passed");
                        intent.putExtra("fails",3-count);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                        intent.putExtra("result","you failed");
                        intent.putExtra("fails",4-count);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
        tofill.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,Fillin_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        chronometer2.stop();
        recordtime=SystemClock.elapsedRealtime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(recordtime!=0){
            chronometer2.setBase(chronometer2.getBase()+SystemClock.elapsedRealtime()-recordtime);
        }
        else {
            chronometer2.setBase(SystemClock.elapsedRealtime());
        }
        chronometer2.start();
    }

    void init(){
        countnum=(TextView)findViewById(R.id.count);
        submit=(Button)findViewById(R.id.submit);
        tofill=(Button)findViewById(R.id.tofillin);
        box1=(CheckBox)findViewById(R.id.box1);
        box2=(CheckBox)findViewById(R.id.box2);
        box3=(CheckBox)findViewById(R.id.box3);
        box4=(CheckBox)findViewById(R.id.box4);
        box5=(CheckBox)findViewById(R.id.box5);
        box6=(CheckBox)findViewById(R.id.box6);
        chronometer1=(Chronometer)findViewById(R.id.chronometer1);
        chronometer2=(Chronometer)findViewById(R.id.chronometer2);
    }
}
