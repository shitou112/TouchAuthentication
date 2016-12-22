package com.xidian.qsf.touchauthencation;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class FeatureVector implements Serializable{
    protected double[] features;
    protected int classLabel;

    public FeatureVector(FeatureVector featureVector){
        this.features = new double[featureVector.features.length];
        for(int i = 0;i<featureVector.features.length;++i){
            this.features[i] = featureVector.features[i];
        }
        this.classLabel = featureVector.classLabel;
    }

    public FeatureVector(int numFeatures) throws IllegalArgumentException {
        if (numFeatures < 1)
            throw new IllegalArgumentException("numFeatures cannot be <= 0");
        this.features = new double[numFeatures];
    }

    public int size(){
        return this.features.length;
    }

    public int getClassLabel(){
        return this.classLabel;
    }
    public void setClassLabel(int _classLabel){
        this.classLabel = _classLabel;
    }

    public double get(int index) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index >= features.length)
            throw new ArrayIndexOutOfBoundsException(String.valueOf(index) +
                    " out of bounds for FeatureVector with size "+ this.size());
        return features[index];
    }

    public double get(int index, double _default) {
        if (index < 0 || index >= features.length)
            return _default;
        return features[index];
    }

    public void set(int index, double value)
            throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= features.length)
            throw new ArrayIndexOutOfBoundsException(String.valueOf(index) +
                    " out of bounds for FeatureVector with size "+ this.size());
        features[index] = value;
    }

    public double[] getAll() {
        return Arrays.copyOf(features, features.length);
    }
}
