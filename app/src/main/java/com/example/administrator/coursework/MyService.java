package com.example.administrator.coursework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
        new Thread(new Runnable(){
            @Override
            public void run(){
                Intent intent=new Intent("changeTime");
                while (Integer.valueOf(MainActivity.time_set)!=0){
                    try{
                        Thread.sleep(1000);
                        MainActivity.time_set=String.valueOf(Integer.valueOf(MainActivity.time_set)-1);
                        sendBroadcast(intent);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Looper.prepare();
                Toast.makeText(MyService.this,"time out, finish",Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
