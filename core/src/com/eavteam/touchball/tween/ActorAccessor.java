package com.eavteam.touchball.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.actors.BallRoundActor;

public class ActorAccessor implements TweenAccessor<Actor> {

    public static final int SIZE = 0;

    @Override
    public int getValues(Actor target, int tweenType, float[] returnValues) {
        switch (tweenType){
            case SIZE :
                returnValues[0] =((BallRoundActor) target).getSize();
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Actor target, int tweenType, float[] newValues) {
        switch (tweenType){
            case SIZE:
                ((BallRoundActor) target).setSize(newValues[0]);
                break;
            default:
                assert false;
        }

    }
}
