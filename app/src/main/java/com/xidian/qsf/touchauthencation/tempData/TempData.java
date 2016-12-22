package com.xidian.qsf.touchauthencation.tempData;

import com.xidian.qsf.touchauthencation.FeatureVector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Administrator on 2016/11/15.
 */

public class TempData {
    public static Queue<FeatureVector> featureVectors = new LinkedList<>();
    public static FeatureVector getFeature(){
        return featureVectors.remove();
    }
    public static void clear(){
        featureVectors.clear();
    }
}
