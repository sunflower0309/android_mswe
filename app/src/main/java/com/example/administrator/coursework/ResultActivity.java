package com.example.administrator.coursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result=(TextView)findViewById(R.id.result);
        Intent intent=getIntent();
        String res=intent.getStringExtra("result");
        result.setText(res);
    }
}
