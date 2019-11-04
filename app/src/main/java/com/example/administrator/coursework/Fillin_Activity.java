package com.example.administrator.coursework;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class Fillin_Activity extends AppCompatActivity {
    private EditText answer;
    private String trueans="call of duty";
    private Button tocho;
    private Button submit;
    private Chronometer chronometer1;
    private Chronometer chronometer2;
    private long recordtime=0;
    private int count=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillin);
        answer=(EditText)findViewById(R.id.answer);
        tocho=(Button)findViewById(R.id.tocho);
        submit=(Button)findViewById(R.id.submit);
        chronometer1=(Chronometer)findViewById(R.id.chronometer1);
        chronometer2=(Chronometer)findViewById(R.id.chronometer2);
        chronometer1.setBase(SystemClock.elapsedRealtime());
        chronometer1.start();
        chronometer2.setBase(SystemClock.elapsedRealtime());
        chronometer2.start();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans=answer.getText().toString();
                if(count>1){
                    if (ans.equalsIgnoreCase(trueans)){
                        Intent intent=new Intent(Fillin_Activity.this,ResultActivity.class);
                        intent.putExtra("result","you passed");
                        startActivity(intent);
                    }
                    else {
                        count-=1;
                        Toast.makeText(Fillin_Activity.this,"you are wrong, "+count+" times left",Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    if (ans.equalsIgnoreCase(trueans)){
                        Intent intent=new Intent(Fillin_Activity.this,ResultActivity.class);
                        intent.putExtra("result","you passed");
                        intent.putExtra("fails",1);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent=new Intent(Fillin_Activity.this,ResultActivity.class);
                        intent.putExtra("result","you failed");
                        intent.putExtra("fails",2);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        tocho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fillin_Activity.this,MainActivity.class);
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
}
