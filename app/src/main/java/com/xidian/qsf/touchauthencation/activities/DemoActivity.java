package com.xidian.qsf.touchauthencation.activities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xidian.qsf.touchauthencation.Parameters;
import com.xidian.qsf.touchauthencation.R;
import com.xidian.qsf.touchauthencation.dao.DatabaseHelper;
import com.xidian.qsf.touchauthencation.secure.TouchEventLive;
import com.xidian.qsf.touchauthencation.entity.UserBehavoir;
import com.xidian.qsf.touchauthencation.secure.SecureBaseInit;



/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class DemoActivity extends TouchAuthActivity{
    private SecureBaseInit mTouchAuthInit = null;
    private TextView mtextView,mPositiveTextView;
    private EditText user_uid_text;

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

        user_uid_text = (EditText) findViewById(R.id.user_uid);

        user_uid_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    user_uid_text.setText("");
                }
                else {
                    user_uid_text.setText("username");
                }
            }
        });

        user_uid_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                UserBehavoir.setUid(s.toString());
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();


        //设置运行状态
        Parameters.runing_state = true;
        System.out.println("start");
        mTouchAuthInit = new SecureBaseInit(new TouchEventLive());
        mTouchAuthInit.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        Parameters.runing_state = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    public void onClick_Event(View view){
        System.out.println("....");
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqldb = databaseHelper.getWritableDatabase();
        databaseHelper.delete(sqldb);

    }

}
