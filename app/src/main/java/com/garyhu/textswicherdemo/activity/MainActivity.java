package com.garyhu.textswicherdemo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.garyhu.textswicherdemo.widget.AutoTextView;
import com.garyhu.textswicherdemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AutoTextView mTextView02;
    private static int sCount = 0;

    private static final int TAG = 0x01;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TAG:
                    sCount++;
                    if(sCount>10){
                        sCount = 0;
                    }
                    mTextView02.setText(sCount%2==0 ?
                            sCount+"AAFirstAA" :
                            sCount+"BBBBBBB");
                    handler.sendEmptyMessageDelayed(TAG,2000);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.swicher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TextSwichActivity.class));
            }
        });
        findViewById(R.id.net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,VerticalBannerActivity.class));
            }
        });
        mTextView02 = (AutoTextView) findViewById(R.id.switcher02);
        mTextView02.setText("点击开始动画");
        mTextView02.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        handler.sendEmptyMessage(TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler.hasMessages(TAG)){
            handler.removeMessages(TAG);
        }
    }

}
