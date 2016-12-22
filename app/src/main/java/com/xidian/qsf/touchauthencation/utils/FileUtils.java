package com.xidian.qsf.touchauthencation.utils;

import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.xidian.qsf.touchauthencation.FeatureVector;
import com.xidian.qsf.touchauthencation.Parameters;
import com.xidian.qsf.touchauthencation.acquiredata.Generator;
import com.xidian.qsf.touchauthencation.acquiredata.TouchEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.classifiers.pmml.consumer.GeneralRegression;


/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public class FileUtils {
    public static boolean read_negative_num_state = false;
    public static boolean read_positive_num_state = false;
    public static String FILE_FEATURE_NUM_NAME = Environment.getExternalStorageDirectory()+"/feature_num.txt";
    public static String FILE_NEGATIVE_FEATURE_NUM_NAME = Environment.getExternalStorageDirectory()+"/negativefeature_num.txt";
    public static String FILE_FEATUREVECTURE_NAME = Environment.getExternalStorageDirectory()+"/positive_user_fv.csv";
    public static String FILE_NEGATIVE_FEATURE_NAME = Environment.getExternalStorageDirectory()+"/negative_user.csv";

    public static String TAG = "FileUtils";
    public static void writeFeatureVector(String fileName, FeatureVector fv){
        Log.i("TAG","writeFeatureVector file:"+fileName);
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(fileName, true), '\t');
            String[] strArr =new String[fv.size()];
            int i = 0;
            for(;i<fv.size();++i){
                String str = String.format("%.2f",fv.get(i));
                strArr[i] = str;
            }
            writer.writeNext(strArr);
            writer.close();
        } catch (IOException e) {
            Log.e(TAG,"特征向量写入时发生异常");
            e.printStackTrace();
        }
    }
    public static List<FeatureVector> readFeatureVectors(String fileName){
//        Log.i("TAG","readFeatureVectors file:"+fileName);
        List<FeatureVector> listFV = new ArrayList<>();
        try {
            int classlabel = -1;
            CSVReader reader = new CSVReader(new FileReader(fileName),'\t');
            String[] strs = reader.readNext();
            if (fileName.contains("positive"))
                 classlabel = 1;
            List<String[]> list = reader.readAll();
            for(String[] ss : list){
                FeatureVector fv = new FeatureVector(TouchEvent.NUM_FEATURES);
                fv.setClassLabel(classlabel);
                for(int i=0;i<ss.length;++i) {
                    if (null != ss[i] && !ss[i].equals(""))
                        fv.set(i,Double.parseDouble(ss[i]));
                }
                listFV.add(fv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFV;
    }
    public static void writeFeatureNum(String filename, int number){
        Log.i("FileUtils","writeFeatureNum file:"+filename);
        try {
            File file = new File(filename);
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(filename,false);
            writer.write(String.valueOf(number));
            writer.close();
        } catch (IOException e) {
            Log.e(TAG,"写入特征向量数量时发生错误");
            e.printStackTrace();
        }
    }
    public static int readFeatureNum(String filename){
//        Log.i("TAG","readFeatureNum file:"+filename);
        int num = 0;
        try {
            File file = new File(filename);
            if (!file.exists()){
                file.createNewFile();

            }
            else {
                if (filename.contains("negative")) {
                    read_negative_num_state = true;
                }
                else {
                    read_positive_num_state = true;
                }
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String numstr = reader.readLine();
                if (numstr!=null){
                    numstr=numstr.trim();
                }
                if(!numstr.equals("")){
                    num = Integer.parseInt(numstr);
                }
                else {
                    num = 0;
                }
                reader.close();
            }
        } catch (Exception e) {
            Log.e(TAG,"读取特征向量数量出错");
        }
        return num;
    }
}
