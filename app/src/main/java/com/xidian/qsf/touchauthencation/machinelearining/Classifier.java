package com.xidian.qsf.touchauthencation.machinelearining;


import com.xidian.qsf.touchauthencation.FeatureVector;

import java.util.List;

/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public abstract class Classifier {
    public abstract boolean train(List<FeatureVector> featureVectors);
    public abstract int classify(FeatureVector featureVector);
}
