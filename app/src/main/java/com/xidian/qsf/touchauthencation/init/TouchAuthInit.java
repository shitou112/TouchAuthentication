package com.xidian.qsf.touchauthencation.init;


import com.xidian.qsf.touchauthencation.TouchAuth;
import com.xidian.qsf.touchauthencation.acquiredata.Dispatcher;
import com.xidian.qsf.touchauthencation.acquiredata.TouchEvent;
import com.xidian.qsf.touchauthencation.machinelearining.BpDeepClassifier;
import com.xidian.qsf.touchauthencation.machinelearining.KNNClassifier;
import com.xidian.qsf.touchauthencation.machinelearining.SVMClassifier;

/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class TouchAuthInit extends TouchAuth {

    public TouchAuthInit(TouchEvent touchEvent) {
        super();
        setDispatcher(new Dispatcher(touchEvent));
//        useClassifier(new BpDeepClassifier(new int[]{25,30,2}, 0.15, 0.8));
        useClassifier(new SVMClassifier(TouchEvent.NUM_FEATURES));
//        useClassifier(new KNNClassifier(7, TouchEvent.NUM_FEATURES));
    }
}
