package com.example.administrator.coursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fillin_Activity extends AppCompatActivity {
    private EditText answer;
    private String trueans="call of duty";
    private Button tocho;
    private Button submit;
    private int count=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillin);
        answer=(EditText)findViewById(R.id.answer);
        tocho=(Button)findViewById(R.id.tocho);
        submit=(Button)findViewById(R.id.submit);
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
                        Toast.makeText(Fillin_Activity.this,"you are wrong, "+count+" times left",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if (ans.equalsIgnoreCase(trueans)){
                        Intent intent=new Intent(Fillin_Activity.this,ResultActivity.class);
                        intent.putExtra("result","you passed");
                        startActivity(intent);
                    }
                    else {
                        Intent intent=new Intent(Fillin_Activity.this,ResultActivity.class);
                        intent.putExtra("result","you failed");
                        startActivity(intent);
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
}
