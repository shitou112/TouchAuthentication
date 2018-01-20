package com.xidian.qsf.touchauthencation.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.xidian.qsf.touchauthencation.entity.UserBehavoir;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Qian Shaofeng on 2017/8/15.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private String str = "duration REAL, end_to_end_distance REAL, mean_length REAL," +
            "twenty_percent_velocity REAL, fifty_percent_velocity REAL," +
            "eighty_percent_velocity REAL, twenty_percent_acceleration REAL";


    private static String DATABASE_NAME = "implicate_authentication";

    private static int VERSION = 2;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user_data" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, uid TEXT, " +
                "start_x REAL, start_y REAL, end_x REAL, end_y REAL,"+
                "duration REAL, direct_end_to_end REAL, mean_length REAL, twenty_velocity REAL, " +
                "fifty_velocity REAL, eighty_velocity REAL, mean_velocity REAL," +
                "twenty_acceleration REAL, fifty_acceleration REAL,"+
                "eighty_acceleration REAL, direction_end_to_end_line REAL,"+
                "trajectory_length REAL, pressure_middle_stroke REAL,"+
                "middle_stroke_area REAL, ratio_distance_traj REAL,"+
                "phone_orientation REAL, flag_direction REAL,"+
                "largest_deviation REAL, twenty_deviation REAL,"+
                "fifty_deviation REAL, eighty_deviation REAL,"+
                "touch_time INTEGER"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void delete(SQLiteDatabase db){
        db.execSQL("drop table if exists user_data");
        Log.i("OUTPUT", "delete table");
    }

    public void insert(UserBehavoir userBehavoir) {
        ContentValues cv=new ContentValues();

        cv.put("uid", userBehavoir.getUid());
        cv.put("start_x", userBehavoir.getStart_x()); //0
        cv.put("start_y", userBehavoir.getStart_y()); //1
        cv.put("end_x", userBehavoir.getEnd_x());//2
        cv.put("end_y", userBehavoir.getEnd_y());//3

        cv.put("duration", userBehavoir.getDuration());//4
        cv.put("direct_end_to_end", userBehavoir.getDirect_end_to_end_distance());//5
        cv.put("mean_length", userBehavoir.getMean_length());//6

        cv.put("twenty_velocity", userBehavoir.getTwenty_velocity());//7
        cv.put("fifty_velocity", userBehavoir.getFifty_velocity());//8
        cv.put("eighty_velocity", userBehavoir.getEighty_velocity());//9
        cv.put("mean_velocity", userBehavoir.getMean_velocity());//10
        cv.put("twenty_acceleration", userBehavoir.getTwenty_acceleration());//11
        cv.put("fifty_acceleration", userBehavoir.getFifty_acceleration());//12
        cv.put("eighty_acceleration", userBehavoir.getEighty_acceleration());//13

        cv.put("direction_end_to_end_line", userBehavoir.getDirection_end_to_end_line());//14
        cv.put("trajectory_length", userBehavoir.getTrajectory_length());//15

        cv.put("pressure_middle_stroke", userBehavoir.getPressure_middle_stroke());//16
        cv.put("middle_stroke_area", userBehavoir.getMiddle_stroke_area());//17

        cv.put("phone_orientation", userBehavoir.getPhone_orientation());//18
        cv.put("flag_direction", userBehavoir.getFlag_direction());//19
        cv.put("ratio_distance_traj", userBehavoir.getRatio_distance_traj());//20

        cv.put("largest_deviation", userBehavoir.getLargest_deviation());//21
        cv.put("twenty_deviation", userBehavoir.getTwenty_deviation());//22
        cv.put("fifty_deviation", userBehavoir.getFifty_deviation());//23
        cv.put("eighty_deviation", userBehavoir.getEighty_deviation());//24

        userBehavoir.setTouch_time(new Date());
        cv.put("touch_time", userBehavoir.getTouch_time().getTime());


        getWritableDatabase().insert("user_data", null, cv);
        System.out.println("insert success");
    }



}
