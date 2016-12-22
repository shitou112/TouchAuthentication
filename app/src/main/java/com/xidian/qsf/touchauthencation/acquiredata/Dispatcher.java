package com.xidian.qsf.touchauthencation.acquiredata;


import android.util.Log;

import com.xidian.qsf.touchauthencation.FeatureVector;
import com.xidian.qsf.touchauthencation.Parameters;
import com.xidian.qsf.touchauthencation.TouchAuth;
import com.xidian.qsf.touchauthencation.tempData.TempData;
import com.xidian.qsf.touchauthencation.utils.FileUtils;

/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class Dispatcher {
    private Generator mGenerator;
    private FeatureVector fv = null;
    private static int num = 0;
    public Dispatcher(Generator generator){
        this.mGenerator = generator;
    }
    public void process(Object ev){
        if (mGenerator.process(ev)){
            if(Parameters.Write_FeatureVector_State==0) {
                fv = mGenerator.getFeatureVector();
                num = Parameters.getDatanum(FileUtils.FILE_FEATURE_NUM_NAME);
                System.out.println("num:" + num + "  parameter:" + Parameters.DATANUM);
                if (num < Parameters.DATANUM) {
                    Log.i("Dispatchere运行模式"+Parameters.Write_FeatureVector_State, "添加一个积极的特征向量");
                    new Thread() {
                        @Override
                        public void run() {
                            FileUtils.writeFeatureVector(FileUtils.FILE_FEATUREVECTURE_NAME, fv);
                            num += 1;
                            FileUtils.writeFeatureNum(FileUtils.FILE_FEATURE_NUM_NAME, num);
                        }
                    }.start();
                } else {
                    Log.i("Dispatchere运行模式"+Parameters.Write_FeatureVector_State, "提交一个临时待分类特征");
                    TempData.featureVectors.add(fv);
                    TouchAuth.classifyFeatureVector = true;
                }
            }
            else{
                fv = mGenerator.getFeatureVector();
                num = Parameters.getDatanum(FileUtils.FILE_NEGATIVE_FEATURE_NUM_NAME);
                System.out.println("num:" + num + "  parameter:" + Parameters.DATANUM);
                if (num < Parameters.DATANUM) {
                    Log.i("Dispatchere运行模式"+Parameters.Write_FeatureVector_State, "添加一个消极的特征向量");
                    new Thread() {
                        @Override
                        public void run() {
                            FileUtils.writeFeatureVector(FileUtils.FILE_NEGATIVE_FEATURE_NAME, fv);
                            num += 1;
                            FileUtils.writeFeatureNum(FileUtils.FILE_NEGATIVE_FEATURE_NUM_NAME, num);
                        }
                    }.start();
                }
            }
        }

    }
}
