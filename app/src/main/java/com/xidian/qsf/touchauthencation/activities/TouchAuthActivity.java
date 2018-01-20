package com.xidian.qsf.touchauthencation.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.xidian.qsf.touchauthencation.FeatureVector;
import com.xidian.qsf.touchauthencation.secure.SecureBase;


public class TouchAuthActivity extends AppCompatActivity {
    public FeatureVector fv = null;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        SecureBase.getTouchAuth().getDispatcher().process(ev, this);
        return super.dispatchTouchEvent(ev);
    }


}
