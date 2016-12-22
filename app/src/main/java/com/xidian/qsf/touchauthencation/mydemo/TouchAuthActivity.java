package com.xidian.qsf.touchauthencation.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.xidian.qsf.touchauthencation.FeatureVector;
import com.xidian.qsf.touchauthencation.TouchAuth;


public class TouchAuthActivity extends AppCompatActivity {
    public FeatureVector fv = null;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TouchAuth.getTouchAuth().getDispatcher().process(ev);
        return super.dispatchTouchEvent(ev);
    }
}
