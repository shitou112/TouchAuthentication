package com.xidian.qsf.touchauthencation.secure;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xidian.qsf.touchauthencation.FeatureVector;
import com.xidian.qsf.touchauthencation.dao.DatabaseHelper;
import com.xidian.qsf.touchauthencation.entity.UserBehavoir;

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
    public void process(Object ev, Context context){
        if (mGenerator.process(ev)){
            fv = mGenerator.getFeatureVector();

            DatabaseHelper dbh = new DatabaseHelper(context);
            UserBehavoir userBehavoir = UserBehavoir.setValues(UserBehavoir.getUid(), fv.getAll());


            SQLiteDatabase db = dbh.getWritableDatabase();
//                dbh.delete(db);
            dbh.onCreate(db);
            dbh.insert(userBehavoir);

            System.out.println("success!");
        }

    }
}
