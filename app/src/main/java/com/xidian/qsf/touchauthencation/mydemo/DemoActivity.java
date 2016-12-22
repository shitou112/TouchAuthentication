package com.xidian.qsf.touchauthencation.mydemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xidian.qsf.touchauthencation.Parameters;
import com.xidian.qsf.touchauthencation.R;
import com.xidian.qsf.touchauthencation.acquiredata.TouchEventLive;
import com.xidian.qsf.touchauthencation.init.TouchAuthInit;
import com.xidian.qsf.touchauthencation.tempData.TempData;
import com.xidian.qsf.touchauthencation.utils.FileUtils;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class DemoActivity extends TouchAuthActivity{
    private TouchAuthInit mTouchAuthInit = null;
    private TextView mtextView,mPositiveTextView;
    private Thread classifyThread;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                mtextView.setText("第"+msg.arg2+"次得分:"+msg.arg1);
            }
            if (msg.what == 2){
                mPositiveTextView.setText(msg.arg1+"");
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mtextView = (TextView) findViewById(R.id.classifyscore_textview);
        mPositiveTextView = (TextView) findViewById(R.id.positive_textview);

        LinearLayout ll = (LinearLayout) findViewById(R.id.paintlayout);
        View view = new PaintView(this);
        ll.addView(view);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //设置写入模式，写入消极的特征向量到文件中
        Parameters.Write_FeatureVector_State = 0;
        //设置运行状态
        Parameters.runing_state = true;
        System.out.println("start");
        mTouchAuthInit = new TouchAuthInit(new TouchEventLive());
        mTouchAuthInit.start();

        classifyThread = new Thread(){
            @Override
            public void run() {
                int i = 0;
                while (Parameters.runing_state) {
                    int prenum = 0;
                    if (FileUtils.read_positive_num_state){
                        int num = Parameters.getDatanum(FileUtils.FILE_FEATURE_NUM_NAME);
                        if (prenum != num) {
                            Message message = Message.obtain();
                            message.what = 2;
                            message.arg1 = num;
                            handler.sendMessage(message);
                        }
                    }
                    int score = mTouchAuthInit.getScores();
                    if (score != 0) {
                        Message message = Message.obtain();
                        message.what = 1;
                        message.arg1 = score;
                        message.arg2  = ++i;
                        handler.sendMessage(message);
                    }
                }
            }
        };
        classifyThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Parameters.runing_state = false;
        TempData.clear();
        classifyThread.interrupt();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



}
