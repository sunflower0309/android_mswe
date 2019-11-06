package com.example.administrator.coursework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button submit;
    private Button tofill;
    private Button settimer;
    private Switch timerswitch;
    private CheckBox box1;
    private CheckBox box2;
    private CheckBox box3;
    private CheckBox box4;
    private CheckBox box5;
    private CheckBox box6;
    private TextView countnum;
    private TextView counttime;
    private Chronometer chronometer1;
    private Chronometer chronometer2;

    private long recordtime=0;
    private int count=4;
    public static String time_set;
    private boolean timer_is_set=false;
    private boolean clock_is_using=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        countnum.setText(String.valueOf(count));
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("changeTime");
        registerReceiver(broadcastReceiver,intentFilter);
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

                    }
                    else{
                        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                        intent.putExtra("result","you failed");
                        intent.putExtra("fails",4-count);
                        startActivity(intent);

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
        settimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                View textEntryView = factory.inflate(R.layout.time_limit_input, null);

                final EditText mname_edit = (EditText) textEntryView.findViewById(R.id.ed);
                new AlertDialog.Builder(MainActivity.this).setTitle("Set Time Limit")
                        .setView(textEntryView)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (!mname_edit.getText().toString().equals("")) {
                                    time_set = mname_edit.getText().toString();
                                    timer_is_set=true;
                                    counttime.setText(time_set);
                                    startService(new Intent(MainActivity.this,MyService.class));
                                }
                            }
                        }).show();

            }
        });

        timerswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    clock_is_using=true;
                    chronometer1.setBase(SystemClock.elapsedRealtime());
                    chronometer2.setBase(SystemClock.elapsedRealtime());
                    chronometer1.start();
                    chronometer2.start();
                }
                else {
                    clock_is_using=false;
                    chronometer1.stop();
                    chronometer1.setBase(SystemClock.elapsedRealtime());
                    chronometer2.stop();
                    chronometer2.setBase(SystemClock.elapsedRealtime());
                }
            }
        });

    }
    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(Integer.valueOf(time_set)==0){
                stopService(new Intent(MainActivity.this,MyService.class));
                Intent intent1=new Intent(MainActivity.this,ResultActivity.class);
                intent1.putExtra("result","you failed");
                intent1.putExtra("fails",4-count);
                startActivity(intent1);
            }
            counttime.setText(time_set);
        }
    };
    @Override
    protected void onStop() {
        super.onStop();
        chronometer2.stop();
        recordtime=SystemClock.elapsedRealtime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(recordtime!=0&&clock_is_using){
            chronometer2.setBase(chronometer2.getBase()+SystemClock.elapsedRealtime()-recordtime);
            chronometer2.start();
        }
        else {
            chronometer2.setBase(SystemClock.elapsedRealtime());
        }
        if(timer_is_set&&Integer.valueOf(time_set)==0){

            stopService(new Intent(MainActivity.this,MyService.class));
            Intent intent1=new Intent(MainActivity.this,ResultActivity.class);
            intent1.putExtra("result","you failed");
            intent1.putExtra("fails",4-count);
            startActivity(intent1);
        }
    }

    void init(){
        countnum=(TextView)findViewById(R.id.count);
        counttime=(TextView)findViewById(R.id.counttime);
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

        timerswitch=(Switch)findViewById(R.id.timerswitch);
        settimer=(Button)findViewById(R.id.settimer);
    }
}
