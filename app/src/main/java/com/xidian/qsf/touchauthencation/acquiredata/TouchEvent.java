package com.xidian.qsf.touchauthencation.acquiredata;

import com.xidian.qsf.touchauthencation.FeatureVector;
import com.xidian.qsf.touchauthencation.utils.ArrayUtil;
import com.xidian.qsf.touchauthencation.utils.ComplexNumbers;

import java.util.ArrayList;



/**
 * Created by Qian Shaofeng on 2016/11/13.
 */
public abstract class TouchEvent extends Generator{
    /**
     * 触摸点类，方便计算特征向量
     */
    public class TouchPoint{
        public long eventTimestamp;
        public double xVal,yVal, pressure, width, orientation;
    }

    public static final String featureList[] = {
            "Start X",
            "Start Y",
            "End X",
            "End Y",
            "Duration in ms",
            "Direct end-to-end distance",
            "Mean Resultant Length",
            "20% perc. pairwise velocity",
            "50% perc. pairwise velocity",
            "80% perc. pairwise velocity",
            "20% perc. pairwise acceleration",
            "50% perc. pairwise acceleration",
            "80% perc. pairwise acceleration",
            "Direction of End-to-End line",
            "Length of Trajectory",
            "Average Velocity",
            "Pressure in the middle of stroke",
            "Midstroke area covered",
            "Phone Orientation",
            "Direction Flag",
            "Ratio of Direct Distance and Traj. Length",
            "Largest Deviation from end-end Line (F22)",
            "20% perc. Deviation from end-to-end line",
            "50% perc. Deviation from end-to-end line",
            "80% perc. Deviation from end-to-end line"
    };

    public static final int NUM_FEATURES = featureList.length;

    protected FeatureVector computeFeatureVector(
            ArrayList<TouchPoint> touchPoints) {
        FeatureVector fv = new FeatureVector(NUM_FEATURES);

		/*Feature List*/
        /**Start X (F1)*/
        /**Start Y (F2)*/
        /**End X (F3)*/
        /**End Y (F4)*/
        /**Duration in ms (F5)*/
        /**Direct end-to-end distance (F6)*/
        /**Mean Resultant Length (F7)*/
        /**20% perc. pairwise velocity (F8)*/
        /**50% perc. pairwise velocity (F9)*/
        /**80% perc. pairwise velocity (F10)*/
        /**20% perc. pairwise acceleration (F11)*/
        /**50% perc. pairwise acceleration (F12)*/
        /**80% perc. pairwise acceleration (F13)*/
        /**Direction of End-to-End line (F14)*/
        /**Length of Trajectory (F15)*/
        /**Average Velocity (F16)*/
        /**Pressure in the middle of stroke (F17)*/
        /**Midstroke area covered (F18)*/
        /**Phone Orientation (F19)*/
        /**Direction Flag (F20)*/
        /**Ratio of Direct Distance and Traj. Length (F21)*/
        /**Largest Deviation from end-end Line (F22)*/
        /**20% perc. Deviation from end-to-end line (F23)*/
        /**50% perc. Deviation from end-to-end line (F24)*/
        /**80% perc. Deviation from end-to-end line (F25)*/


        int numPoints = touchPoints.size();
		/*F 1-7 are pretty straightforward*/
        fv.set(0, touchPoints.get(0).xVal); /*startX*/
        fv.set(1, touchPoints.get(0).yVal); /*startY*/
        fv.set(2, touchPoints.get(numPoints - 1).xVal); /*endX*/
        fv.set(3, touchPoints.get(numPoints - 1).yVal); /*endY*/
        fv.set(4, touchPoints.get(numPoints - 1).eventTimestamp -
                touchPoints.get(0).eventTimestamp); /*duration*/
        fv.set(5, Math.sqrt(Math.pow(fv.get(2) - fv.get(0), 2) +
                Math.pow(fv.get(3) - fv.get(1), 2))); /*directDistance*/

		/*Calculate pairwise displacement, velocity and acceleration*/
        double xDisplacement[] = new double[numPoints - 1];
        double yDisplacement[] = new double[numPoints - 1];
        double tDisplacement[] = new double[numPoints - 1];
        double pairwAngle[] = new double[numPoints - 1];
        double pairwDistance[] = new double[numPoints - 1];
        double pairwVelocity[] = new double[numPoints - 1];
        double pairwAcceleration[] = new double[numPoints - 2];

        for (int i = 0; i < numPoints - 2; i++) {
            xDisplacement[i] = touchPoints.get(i+1).xVal -
                    touchPoints.get(i).xVal;
            yDisplacement[i] = touchPoints.get(i+1).yVal -
                    touchPoints.get(i).yVal;
            tDisplacement[i] = touchPoints.get(i+1).eventTimestamp -
                    touchPoints.get(i).eventTimestamp;
            pairwAngle[i] =  Math.atan2(yDisplacement[i], xDisplacement[i]);
            pairwDistance[i] =  Math.sqrt(Math.pow(xDisplacement[i], 2) +
                    Math.pow(yDisplacement[i], 2));
            if (tDisplacement[i] == 0)
                pairwVelocity[i] = 0;
            else
                pairwVelocity[i] = pairwDistance[i]/tDisplacement[i];
        }
		/*correct pairwVelocity by setting '0' to maxVelocity*/
        double maxVelocity = ArrayUtil.max(pairwVelocity);
        for (int i = 0; i < pairwVelocity.length - 1; i++)
            if (pairwVelocity[i] == 0)
                pairwVelocity[i] = maxVelocity;

        for (int i = 0; i < pairwVelocity.length - 2; i++) {
            pairwAcceleration[i] = pairwVelocity[i+1] - pairwVelocity[i];
            if (tDisplacement[i] == 0)
                pairwAcceleration[i] = 0;
            else
                pairwAcceleration[i] = pairwAcceleration[i]/tDisplacement[i];
        }
		/*calculate the max values for acceleration and replace
		 * values for which tDisplacement = 0 to max*/
        double maxAcceleration = 0;

        maxAcceleration = ArrayUtil.max(pairwAcceleration);

        for (int i = 0; i < pairwAcceleration.length - 1; i++)
            if (pairwAcceleration[i] == 0)
                pairwAcceleration[i] = maxAcceleration;

		/*F8-15*/
        fv.set(6, ComplexNumbers.circ_r(pairwAngle)); /*meanResultantLength*/
        fv.set(7, ArrayUtil.percentile(pairwVelocity, 0.20)); /*velocity20*/
        fv.set(8, ArrayUtil.percentile(pairwVelocity, 0.50)); /*velocity50*/
        fv.set(9, ArrayUtil.percentile(pairwVelocity, 0.80)); /*velocity80*/
        fv.set(10, ArrayUtil.percentile(pairwAcceleration, 0.20)); /*acceleration20*/
        fv.set(11, ArrayUtil.percentile(pairwAcceleration, 0.50)); /*acceleration50*/
        fv.set(12, ArrayUtil.percentile(pairwAcceleration, 0.80)); /*acceleration80*/
        fv.set(13, Math.atan2(fv.get(3) - fv.get(1),
                fv.get(2) - fv.get(0))); /*lineDirection*/



		/*F17-18: trajectoryLength & averageVelocity*/
        double temp = 0;

        for (int i = 0; i < pairwDistance.length; i++) {
            temp += pairwDistance[i]; /*trajectoryLength*/
        }
        fv.set(14, temp);

        /*inverse of mean velocity*/
        if(fv.get(14) == 0)
            fv.set(15, 0);
        else
            fv.set(15, fv.get(4)/fv.get(14));



		/*F20-22: midPressure, midArea, phoneOrientation*/
        fv.set(16, touchPoints.get(numPoints/2).pressure);
        fv.set(17, touchPoints.get(numPoints/2).width);
        fv.set(18, touchPoints.get(numPoints/2).orientation);

		/*F23 - Direction Flag. up, down, left, right are 0,1,2,3*/
        fv.set(19, 1);
        double xDiff = fv.get(2) - fv.get(0);
        double yDiff = fv.get(3) - fv.get(1);
        if (Math.abs(xDiff) > Math.abs(yDiff))
            if (xDiff < 0)
                fv.set(19, 2); //left
            else
                fv.set(19, 3); //right
        else
        if (yDiff < 0)
            fv.set(19, 0); //up

		/*F24-25: distToTrajRatio; averageDirection*/
        if (fv.get(14) == 0)
            fv.set(20, 0);
        else
            fv.set(20, fv.get(6)/fv.get(14));


		/*F26-29 - Largest/20%/50%/80% deviation from end-to-end line*/
        double xVek [] = new double[numPoints];
        double yVek [] = new double[numPoints];
        for (int i = 0; i < numPoints - 1; i++) {
            xVek[i] = touchPoints.get(i).xVal - fv.get(0);
            yVek[i] = touchPoints.get(i).yVal - fv.get(1);
        }
        double perVek[] = {yVek[yVek.length-1], xVek[xVek.length-1] - 1, 0};
        temp = Math.sqrt(Math.pow(perVek[0], 2) + Math.pow(perVek[1], 2));
        if (temp == 0)
            perVek[0] = perVek[1] = perVek[2]  = 0;
        else {
            perVek[0] /= temp;
            perVek[1] /= temp;
            perVek[2] /= temp;
        }

        double absProj [] = new double[numPoints];
        for (int i = 0; i < numPoints - 1; i++)
            absProj[i] = Math.abs(xVek[i] * perVek[0] + yVek[i] * perVek[1]);
        fv.set(21, ArrayUtil.max(absProj));
        fv.set(22, ArrayUtil.percentile(absProj, 0.2));
        fv.set(23, ArrayUtil.percentile(absProj, 0.5));
        fv.set(24, ArrayUtil.percentile(absProj, 0.8));

        return fv;
    }
}
