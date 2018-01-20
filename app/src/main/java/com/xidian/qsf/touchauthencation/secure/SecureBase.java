package com.xidian.qsf.touchauthencation.secure;


import com.xidian.qsf.touchauthencation.Parameters;
import com.xidian.qsf.touchauthencation.machinelearining.Classifier;

import java.util.ArrayList;

/**
 * Created by Qian Shaofeng on 2016/11/13.
 * TouchAuth这个类主要是将各个模块的内容整合形成一个整体
 */
public class SecureBase extends Thread{
    String TAG = "TouvhAuth";

    private static SecureBase sSecureBase = null;
    private static Classifier sClassifier = null;
    private Dispatcher mDispatcher;


    private static ArrayList<Integer> scoresList = new ArrayList<Integer>();


    public SecureBase(){
        sSecureBase = this;
    }


    public static SecureBase getTouchAuth(){
        return sSecureBase;
    }
    public Dispatcher getDispatcher(){
        return mDispatcher;
    }
    public void setDispatcher(Dispatcher dispatcher){
        mDispatcher = dispatcher;
    }
    public void useClassifier(Classifier classifier){
        sClassifier = classifier;
    }
    public Classifier getClassifier(){
        return sClassifier;
    }

    public int getScores(){
        if (scoresList.size()>=1) {
            return scoresList.remove(0);
        }
        else
            return 0;
    }

    @Override
    public void run() {

        while(Parameters.runing_state) {

        }
    }
}
