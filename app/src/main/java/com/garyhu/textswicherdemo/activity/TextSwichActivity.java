package com.garyhu.textswicherdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.garyhu.textswicherdemo.R;

/**
 * 作者： garyhu.
 * 时间： 2017/2/22.
 */

public class TextSwichActivity extends AppCompatActivity {


    private TextSwitcher ts;
    private String[] texts = {"这是个嘛","你说你是不是傻","什么时候给我们来个大放假啊","我要想好好的放个假"};
    private int index;

    private static final int TAG = 0x01;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TAG:
                    index++;
                    if(index>3){
                        index = 0;
                    }
                    ts.setText(texts[index]);
                    handler.sendEmptyMessageDelayed(TAG,2000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swicher);
        ts = (TextSwitcher) findViewById(R.id.ts);
        ts.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(TextSwichActivity.this);
                tv.setTextColor(Color.RED);
                tv.setTextSize(20);
                TextSwitcher.LayoutParams lp = new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,60);
                lp.gravity = Gravity.CENTER_VERTICAL;
                lp.leftMargin = 30;
                tv.setLayoutParams(lp);
                return tv;
            }
        });
        ts.setText(texts[0]);
        ts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(TAG);
            }
        });
        startAnim();
    }

    public void startAnim(){
        Animation in = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_top);
        ts.setInAnimation(in);
        ts.setOutAnimation(out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler.hasMessages(TAG)){
            handler.removeMessages(TAG);
        }
    }
}
