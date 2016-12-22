package com.xidian.qsf.touchauthencation.mydemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xidian.qsf.touchauthencation.Parameters;
import com.xidian.qsf.touchauthencation.R;
import com.xidian.qsf.touchauthencation.acquiredata.TouchEventLive;
import com.xidian.qsf.touchauthencation.init.TouchAuthInit;
import com.xidian.qsf.touchauthencation.utils.FileUtils;

public class NegativeFea_Activity extends TouchAuthActivity{
    TouchAuthInit mTouchAuthInit = null;
    TextView mtextView ;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                mtextView.setText(""+msg.arg1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negativefea);
        mtextView = (TextView) findViewById(R.id.negative_textview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Parameters.Write_FeatureVector_State = 1;
        Parameters.runing_state = true;
        System.out.println("start");
        mTouchAuthInit = new TouchAuthInit(new TouchEventLive());
        mTouchAuthInit.start();
        new Thread(){
            @Override
            public void run() {
                int prenum = 0;
                while (Parameters.runing_state) {
                    if(FileUtils.read_negative_num_state) {
                        int num = Parameters.getDatanum(FileUtils.FILE_NEGATIVE_FEATURE_NUM_NAME);
                        if (prenum != num) {
                            Message message = Message.obtain();
                            message.what = 1;
                            message.arg1 = num;
                            handler.sendMessage(message);
                            prenum = num;
                        }
                    }

                    try {
                        Thread.sleep(Parameters.RUNPERIOC);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Parameters.runing_state = false;
    }
}
