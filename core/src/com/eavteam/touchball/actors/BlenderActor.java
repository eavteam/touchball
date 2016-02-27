package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.common.Assets;

/**
 * Created by XEXE on 27.02.2016.
 */
public class BlenderActor extends Actor {
    private Sprite[] blenderSection; //массив хранящий спрайты секций крутилки
    private float angleOfRotation; // угол поворота всей конструкции
    private float speedOfRotation; // скорость поворота всей конструкции
    private int amountOfSections; // количество секций конструкции

    public BlenderActor(){
        setAmountOfSections(25); // устанавливаем количество секций в конструкции
        buildCarcass(); // собираем конструкцию
        setSize(10); // устанавливаем размер
        refreshPosition();
        setSpeedOfRotation(10);
    }

    private void setAmountOfSections(int amount){
        this.amountOfSections = amount;
    }

    private void buildCarcass(){
        blenderSection = new Sprite[amountOfSections];
        for(int i = 0; i < amountOfSections; i++){
            blenderSection[i] = new Sprite(Assets.manager.get(Assets.blenderSection, Texture.class));
            blenderSection[i].setColor(new Color(1f-0.05f*i, 0.09f*i, 0.05f*i, 1f));
            setBounds(blenderSection[i].getX(), blenderSection[i].getY(), blenderSection[i].getWidth(), blenderSection[i].getHeight());
        }
    }

    private void setSpeedOfRotation(float speedOfRotation){
        this.speedOfRotation = speedOfRotation;
    }

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        this.setSize((Gdx.graphics.getHeight() * percent / 100) / Assets.PPM, (Gdx.graphics.getHeight() * 3 * percent / 100) / Assets.PPM);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width,height);
        for(int i = 0; i < amountOfSections; i++) {
            blenderSection[i].setSize(width, height);
            blenderSection[i].setOriginCenter();
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - blenderSection[0].getWidth()/2, y - blenderSection[0].getHeight()/2);
        for(int i = 0; i < amountOfSections; i++)
        this.blenderSection[i].setPosition(x - blenderSection[i].getWidth()/2, y - blenderSection[i].getHeight()/2);
    }

    public void refreshPosition(){
        setPosition((Gdx.graphics.getWidth() / 2)/Assets.PPM, (Gdx.graphics.getHeight() / 2)/Assets.PPM);
    }

    //TODO
    public void setRotation(float omega){

    }

    @Override
    public void draw(Batch batch, float alpha){
        for(int i = 0; i < amountOfSections; i++)
        this.blenderSection[i].draw(batch);
    }

    @Override
    public void act(float delta) {
        angleOfRotation += delta*speedOfRotation;
        super.act(delta);
        for(int i = 0; i < amountOfSections; i++)
        this.blenderSection[i].setRotation(angleOfRotation + i*10);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x,y,touchable);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}
