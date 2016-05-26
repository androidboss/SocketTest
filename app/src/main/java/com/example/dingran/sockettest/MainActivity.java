package com.example.dingran.sockettest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(MyIntentService.ACTION);
        // 不需要用Bundle加参数了，因为任务就一项
        startService(intent);

        Button button = (Button) findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyThread().start();
            }
        });


    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            Client client = new Client();
            client.start();
        }
    }
}
