package com.xidian.qsf.touchauthencation.utils;

import com.xidian.qsf.touchauthencation.FeatureVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/10.
 */

public class DataNormalization {
    private static double[] min;
    private static double[] max;

    public static List<FeatureVector> dataNormalization(List<FeatureVector> featureVectors){
        if (featureVectors==null){
            return null;
        }


        min = new double[featureVectors.get(0).size()];
        max = new double[featureVectors.get(0).size()];
        for (int i=0;i<featureVectors.get(0).size();++i){
            min[i] = 0;
            max[i] = 0;
            for(int j=0;j<featureVectors.size();++j){
                if (min[i]>featureVectors.get(j).get(i)){
                    min[i] = featureVectors.get(j).get(i);
                }
                if (max[i]<featureVectors.get(j).get(i)){
                    max[i] = featureVectors.get(j).get(i);
                }
            }
        }
        double value;
        for (int i=0;i<featureVectors.get(0).size();++i){
            for(int j=0;j<featureVectors.size();++j){
                if (min[i]==max[i]){
                    featureVectors.get(j).set(i, 0);
                }
                else {
                    value = featureVectors.get(j).get(i) - min[i];
                    featureVectors.get(j).set(i, 2*value / (max[i] - min[i])-1);
                }
            }
        }
//        for (int i =0;i<featureVectors.size();++i){
//            for (int j=0;j<featureVectors.get(0).size();++j){
//                System.out.print(featureVectors.get(i).get(j)+", ");
//            }
//            System.out.println();
//        }
        return featureVectors;
    }
    public static FeatureVector fvNormalization(FeatureVector fv){
        for (int i=0;i<fv.size();++i){
            if (min[i] == max[i]){
                fv.set(i, 0);
            }
            else {
                fv.set(i, 2*(fv.get(i) - min[i]) / (max[i] - min[i])-1);
            }
        }
        return fv;
    }
}
