package com.xidian.qsf.touchauthencation;


import com.xidian.qsf.touchauthencation.utils.FileUtils;

/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class Parameters {
    public static final int DATANUM = 30;
    public static final long RUNPERIOC = 1000;

    //设置运行状态参数，启动的时候就运行，否则暂停
    public static boolean runing_state = true;

    //设置写入特征向量状态，0的时候是获得待分类的特征向量或者添加积极的特征向量；1的时候是添加消极的特征向量
    public static int Write_FeatureVector_State = 0;
    public static boolean enoughData(String filename){
        int num = FileUtils.readFeatureNum(filename);
        if(num>=Parameters.DATANUM){
            return true;
        }
        else return false;
    }
    public static int getDatanum(String filename){
        return FileUtils.readFeatureNum(filename);
    }


}
