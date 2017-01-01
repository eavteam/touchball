package com.eavteam.touchball.common;

import com.badlogic.gdx.Gdx;

/**
 * Created by vladimir on 01.01.17.
 */
public class BullitTime {
    private static float slowD = 1;
    private static float time;
    private BullitTime(){
    }

    public static float getDelta(){
        return Gdx.graphics.getDeltaTime()/slowD;
    }

    public static void setSlow(float slow){
        slowD = slow;
    }

}
