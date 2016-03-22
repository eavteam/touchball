package com.eavteam.touchball.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.actors.BallActor;
import com.eavteam.touchball.actors.BallRoundActor;
import com.eavteam.touchball.actors.TargetActor;

public class ActorAccessor implements TweenAccessor<Actor> {

    public static final int BALLROUNDSIZE = 0;
    public static final int BALLSIZE = 1;
    public static final int TARGETSIZE = 2;

    @Override
    public int getValues(Actor target, int tweenType, float[] returnValues) {
        switch (tweenType){
            case BALLROUNDSIZE:
                returnValues[0] =((BallRoundActor) target).getSize();
                return 1;
            case BALLSIZE:
                returnValues[0] =((BallActor) target).getSize();
                return 1;
            case TARGETSIZE:
                returnValues[0] =((TargetActor) target).getSize();
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Actor target, int tweenType, float[] newValues) {
        switch (tweenType){
            case BALLROUNDSIZE:
                ((BallRoundActor) target).setSize(newValues[0]);
                break;
            case BALLSIZE:
                ((BallActor) target).setSize(newValues[0]);
                break;
            case TARGETSIZE:
                ((TargetActor) target).setSize(newValues[0]);
                break;
            default:
                assert false;
        }

    }
}
