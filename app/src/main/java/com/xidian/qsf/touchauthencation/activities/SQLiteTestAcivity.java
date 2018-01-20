package com.xidian.qsf.touchauthencation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xidian.qsf.touchauthencation.R;
import com.xidian.qsf.touchauthencation.dao.DatabaseHelper;

/**
 * Created by Qian Shaofeng on 2017/8/17.
 */

public class SQLiteTestAcivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbh = new DatabaseHelper(this);
//        UserBehavoir userBehavoir = new UserBehavoir();
//        userBehavoir.setStart_x(1.0);
//        userBehavoir.setStart_y(2.0);
//        userBehavoir.setEnd_x(3.0);
//        userBehavoir.setEnd_y(4.0);
//        SQLiteDatabase db = dbh.getWritableDatabase();
//        dbh.onCreate(db);
//        dbh.insert(userBehavoir);

        System.out.println("success!");
    }

    @Override
    protected void onStart() {
        super.onStart();


        System.out.println("success...");
    }
}
