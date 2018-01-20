package com.xidian.qsf.touchauthencation.entity;


import java.util.Date;

/**
 * Created by Qian Shaofeng on 2017/8/15.
 */

public class UserBehavoir {
    private static String uid;
    private double start_x; //0
    private double start_y; //1
    private double end_x; //2
    private double end_y;// 3

    private double direct_end_to_end_distance; //4
    private double duration; //5
    private double mean_length; //6
    
    private double twenty_velocity; //7
    private double fifty_velocity; //8
    private double eighty_velocity; //9
    private double mean_velocity; //10
    private double twenty_acceleration; //11
    private double fifty_acceleration; //12
    private double eighty_acceleration; //13
    
    private double direction_end_to_end_line; //14
    private double trajectory_length; //15

    private double pressure_middle_stroke; //16
    private double middle_stroke_area; //17
    private double ratio_distance_traj; //18
    
    private double phone_orientation; //19
    private double flag_direction;//20

    private double largest_deviation;//21
    private double twenty_deviation;//22
    private double fifty_deviation;//23
    private double eighty_deviation;//24

    private Date touch_time;

    public Date getTouch_time() {
        return touch_time;
    }

    public void setTouch_time(Date touch_time) {
        this.touch_time = touch_time;
    }

    public UserBehavoir(String uid){
        this.uid = uid;
    }

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        UserBehavoir.uid = uid;
    }

    public double getStart_x() {
        return start_x;
    }

    public void setStart_x(double start_x) {
        this.start_x = start_x;
    }

    public double getStart_y() {
        return start_y;
    }

    public void setStart_y(double start_y) {
        this.start_y = start_y;
    }

    public double getEnd_x() {
        return end_x;
    }

    public void setEnd_x(double end_x) {
        this.end_x = end_x;
    }

    public double getEnd_y() {
        return end_y;
    }

    public void setEnd_y(double end_y) {
        this.end_y = end_y;
    }

    public double getDirect_end_to_end_distance() {
        return direct_end_to_end_distance;
    }

    public void setDirect_end_to_end_distance(double direct_end_to_end_length) {
        this.direct_end_to_end_distance = direct_end_to_end_length;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getMean_length() {
        return mean_length;
    }

    public void setMean_length(double mean_length) {
        this.mean_length = mean_length;
    }

    public double getTwenty_velocity() {
        return twenty_velocity;
    }

    public void setTwenty_velocity(double twenty_velocity) {
        this.twenty_velocity = twenty_velocity;
    }

    public double getFifty_velocity() {
        return fifty_velocity;
    }

    public void setFifty_velocity(double fifty_velocity) {
        this.fifty_velocity = fifty_velocity;
    }

    public double getEighty_velocity() {
        return eighty_velocity;
    }

    public void setEighty_velocity(double eighty_velocity) {
        this.eighty_velocity = eighty_velocity;
    }

    public double getTwenty_acceleration() {
        return twenty_acceleration;
    }

    public void setTwenty_acceleration(double twenty_acceleration) {
        this.twenty_acceleration = twenty_acceleration;
    }

    public double getFifty_acceleration() {
        return fifty_acceleration;
    }

    public void setFifty_acceleration(double fifty_acceleration) {
        this.fifty_acceleration = fifty_acceleration;
    }

    public double getEighty_acceleration() {
        return eighty_acceleration;
    }

    public void setEighty_acceleration(double eighty_acceleration) {
        this.eighty_acceleration = eighty_acceleration;
    }

    public double getDirection_end_to_end_line() {
        return direction_end_to_end_line;
    }

    public void setDirection_end_to_end_line(double direction_end_to_end_line) {
        this.direction_end_to_end_line = direction_end_to_end_line;
    }

    public double getTrajectory_length() {
        return trajectory_length;
    }

    public void setTrajectory_length(double trajectory_length) {
        this.trajectory_length = trajectory_length;
    }

    public double getMean_velocity() {
        return mean_velocity;
    }

    public void setMean_velocity(double mean_velocity) {
        this.mean_velocity = mean_velocity;
    }

    public double getPressure_middle_stroke() {
        return pressure_middle_stroke;
    }

    public void setPressure_middle_stroke(double pressure_middle_stroke) {
        this.pressure_middle_stroke = pressure_middle_stroke;
    }

    public double getMiddle_stroke_area() {
        return middle_stroke_area;
    }

    public void setMiddle_stroke_area(double middle_stroke_area) {
        this.middle_stroke_area = middle_stroke_area;
    }

    public double getPhone_orientation() {
        return phone_orientation;
    }

    public void setPhone_orientation(double phone_orientation) {
        this.phone_orientation = phone_orientation;
    }

    public double getFlag_direction() {
        return flag_direction;
    }

    public void setFlag_direction(double flag_direction) {
        this.flag_direction = flag_direction;
    }

    public double getRatio_distance_traj() {
        return ratio_distance_traj;
    }

    public void setRatio_distance_traj(double ratio_distance_traj) {
        this.ratio_distance_traj = ratio_distance_traj;
    }

    public double getLargest_deviation() {
        return largest_deviation;
    }

    public void setLargest_deviation(double largest_deviation) {
        this.largest_deviation = largest_deviation;
    }

    public double getTwenty_deviation() {
        return twenty_deviation;
    }

    public void setTwenty_deviation(double twenty_deviation) {
        this.twenty_deviation = twenty_deviation;
    }

    public double getFifty_deviation() {
        return fifty_deviation;
    }

    public void setFifty_deviation(double fifty_deviation) {
        this.fifty_deviation = fifty_deviation;
    }

    public double getEighty_deviation() {
        return eighty_deviation;
    }

    public void setEighty_deviation(double eighty_deviation) {
        this.eighty_deviation = eighty_deviation;
    }
    
    public static UserBehavoir setValues(String uid, double[] feature){
        
        UserBehavoir newUserBehavoir = new UserBehavoir(uid);
        newUserBehavoir.setStart_x(feature[0]);
        newUserBehavoir.setStart_y(feature[1]);
        newUserBehavoir.setEnd_x(feature[2]);
        newUserBehavoir.setEnd_y(feature[3]);

        newUserBehavoir.setDuration(feature[4]);
        newUserBehavoir.setDirect_end_to_end_distance(feature[5]);
        newUserBehavoir.setMean_length(feature[6]);

        newUserBehavoir.setTwenty_velocity(feature[7]);
        newUserBehavoir.setFifty_velocity(feature[8]);
        newUserBehavoir.setEighty_velocity(feature[9]);
        newUserBehavoir.setTwenty_acceleration(feature[10]);
        newUserBehavoir.setFifty_acceleration(feature[11]);
        newUserBehavoir.setEighty_acceleration(feature[12]);

        newUserBehavoir.setDirection_end_to_end_line(feature[13]);
        newUserBehavoir.setTrajectory_length(feature[14]);
        newUserBehavoir.setMean_velocity(feature[15]);

        newUserBehavoir.setPressure_middle_stroke(feature[16]);
        newUserBehavoir.setMiddle_stroke_area(feature[17]);

        newUserBehavoir.setPhone_orientation(feature[18]);
        newUserBehavoir.setFlag_direction(feature[19]);
        newUserBehavoir.setRatio_distance_traj(feature[20]);

        newUserBehavoir.setLargest_deviation(feature[21]);
        newUserBehavoir.setTwenty_deviation(feature[22]);
        newUserBehavoir.setFifty_deviation(feature[23]);
        newUserBehavoir.setEighty_deviation(feature[24]);
        
        return newUserBehavoir;
    }
}
