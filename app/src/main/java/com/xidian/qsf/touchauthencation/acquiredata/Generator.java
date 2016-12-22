package com.xidian.qsf.touchauthencation.acquiredata;


import com.xidian.qsf.touchauthencation.FeatureVector;

/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public abstract class Generator {
    protected FeatureVector fv;

    public abstract boolean process(Object ev);

    public FeatureVector getFeatureVector(){
        return fv;
    }
}
