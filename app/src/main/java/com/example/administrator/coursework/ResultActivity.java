package com.example.administrator.coursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView result1;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result1=(TextView)findViewById(R.id.result1);
        send=(Button)findViewById(R.id.send);
        Intent intent=getIntent();
        String res=intent.getStringExtra("result");
        final int fails=intent.getIntExtra("fails",0);
        result1.setText(res);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                String[] values={"805786665@qq.com"};
                shareIntent.setType("message/rfc822"); //分享的是文本类型
                shareIntent.putExtra(Intent.EXTRA_EMAIL, values);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "fail times");
                shareIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(fails));
                startActivity(shareIntent);
            }
        });
    }
}
