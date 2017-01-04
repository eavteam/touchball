package com.eavteam.touchball.common;

import com.badlogic.gdx.Gdx;

/**
 * Created by vladimir on 01.01.17.
 */
public class BullitTime {
    private static float slowD = 1;
    private static float timeD = 0;
    private BullitTime(){
    }

    public static float getDelta(){
        return Gdx.graphics.getDeltaTime()/slowD;
    }

    public static void setSlow(float slow, float time){
        if(slow <= 0) return;
        setSlow(slow); timeD = time;
    }

    public static void update(float delta){
        if(timeD < 0) resetSlow();
        timeD -= delta;
    }

    public static void refresh(){
        resetSlow();
    }

    //TODO если надо отдельно, раскоментить другой update
    private static void setSlow(float slow){
        slowD = slow;
    }
    private static void resetSlow(){
        slowD = 1;timeD = 0;
    }

//    public static void update(float delta){
//        if(timeD <= 0) timeD = 0;
//        else if(timeD - delta < 0) resetSlow();
//        timeD -= delta;
//    }
}
