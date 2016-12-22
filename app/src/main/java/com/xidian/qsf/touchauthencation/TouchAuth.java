package com.xidian.qsf.touchauthencation;


import android.os.Message;
import android.util.Log;

import com.xidian.qsf.touchauthencation.acquiredata.Dispatcher;
import com.xidian.qsf.touchauthencation.machinelearining.Classifier;
import com.xidian.qsf.touchauthencation.tempData.TempData;
import com.xidian.qsf.touchauthencation.utils.DataNormalization;
import com.xidian.qsf.touchauthencation.utils.FileUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * TouchAuth这个类主要是将各个模块的内容整合形成一个整体
 */
public class TouchAuth extends Thread{
    String TAG = "TouvhAuth";

    private static TouchAuth sTouchAuth = null;
    private static Classifier sClassifier = null;
    private Dispatcher mDispatcher;

    private boolean trainingFlag = false;
    public static boolean classifyFeatureVector = false;

    DecimalFormat df = new DecimalFormat("0.00");
    private static ArrayList<Integer> scoresList = new ArrayList<Integer>();


    public TouchAuth(){
        sTouchAuth = this;
    }


    public static TouchAuth getTouchAuth(){
        return sTouchAuth;
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
            try {
                if (classifyFeatureVector) {
                    System.out.println("classify");
                    System.out.println("trainflag:"+trainingFlag);
                    int num = Parameters.getDatanum(FileUtils.FILE_FEATURE_NUM_NAME);
                    if (num == Parameters.DATANUM) {
                        if (!trainingFlag) {

                            List<FeatureVector> positive_listFv = FileUtils.readFeatureVectors(FileUtils.FILE_FEATUREVECTURE_NAME);
                            List<FeatureVector> negative_listFv = FileUtils.readFeatureVectors(FileUtils.FILE_NEGATIVE_FEATURE_NAME);

                            //向positive集合中添加negative特征，合并所有的特征
                            positive_listFv.addAll(negative_listFv);
                            positive_listFv = DataNormalization.dataNormalization(positive_listFv);
                            sClassifier.train(positive_listFv);
                            System.out.println("a:"+positive_listFv.get(0).features.length);
                            trainingFlag = true;
                        }
                        FeatureVector fv = TempData.getFeature();
                        if(fv!=null) {
                            fv = DataNormalization.fvNormalization(fv);
                            System.out.println("t:" + fv.features.length);
                            int classifyScore = sClassifier.classify(fv);
                            System.out.println("score:" + classifyScore);
                            scoresList.add(classifyScore);
                        }


                    }
                    classifyFeatureVector = false;
                }

                Thread.sleep(Parameters.RUNPERIOC);
            } catch (Exception e) {
                Log.e("DemoActivityException","Exception");
            }
        }
    }
}
