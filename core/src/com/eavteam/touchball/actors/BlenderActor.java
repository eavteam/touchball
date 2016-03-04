package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.common.Assets;

/**
 * Created by XEXE on 27.02.2016.
 */
public class BlenderActor extends Actor {
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Body[] body;
    private Sprite[] blenderSection; //массив хранящий спрайты секций конструкции
    private float angleOfRotation; // угол поворота всей конструкции в градусах хуельсия
    private float speedOfRotation; // скорость поворота всей конструкции
    private int amountOfSections; // количество секций конструкции
    private float stepChangeOfColor; //шаг изменения цвета
    private float speedChangeOfColor; //скорость изменения цвета
    boolean[] changeR; boolean[] changeG; boolean[] changeB;
    float x; // кто так программирует? бла бла бла

    public BlenderActor(){
        setStepChangeOfColor(12f);
        setSpeedChangeOfColor(100f);
        setAmountOfSections(30); // устанавливаем количество секций в конструкции
        buildCarcass(); // собираем конструкцию
        setSize(10); // устанавливаем размер
        refreshPosition();
        setSpeedOfRotation(30);
    }

    private void setAmountOfSections(int amount){
        this.amountOfSections = amount;
    }
    private void setSpeedOfRotation(float speedOfRotation){
        this.speedOfRotation = speedOfRotation;
    }
    private void setStepChangeOfColor(float stepChangeOfColor){
        this.stepChangeOfColor = stepChangeOfColor;
    }
    private void setSpeedChangeOfColor(float speedChangeOfColor){
        this.speedChangeOfColor = speedChangeOfColor;
    }

    private void buildCarcass(){
        body = new Body[amountOfSections];
        blenderSection = new Sprite[amountOfSections];
        changeR = new boolean[amountOfSections];
        changeG = new boolean[amountOfSections];
        changeB = new boolean[amountOfSections];
        float r = 1f; float g = 0f; float b = 0f;
        boolean changeR = false;boolean changeG = true;boolean changeB = false;
        for(int i = 0; i < amountOfSections; i++){
            blenderSection[i] = new Sprite(Assets.manager.get(Assets.blenderSection, Texture.class));
            if(r >= 1f){changeR = false; changeG = true; changeB = false; r = 1f; g = 0f; b = 0f;
                for(int j = i; j < amountOfSections; j++){this.changeR[j] = false; this.changeG[j] = true; this.changeB[j] = false;}
            }
            if(g >= 1f){changeR = false; changeG = false; changeB = true; r = 0f; g = 1f; b = 0f;
                for(int j = i; j < amountOfSections; j++){this.changeR[j] = false; this.changeG[j] = false; this.changeB[j] = true;}
            }
            if(b >= 1f){changeR = true; changeG = false; changeB = false; r = 0f; g = 0f; b = 1f;
                for(int j = i; j < amountOfSections; j++){this.changeR[j] = true; this.changeG[j] = false; this.changeB[j] = false;}
            }
            if(changeR){b -= 1 / stepChangeOfColor; r += 1 / stepChangeOfColor;}
            if(changeG){r -= 1 / stepChangeOfColor; g += 1 / stepChangeOfColor;}
            if(changeB){g -= 1 / stepChangeOfColor; b += 1 / stepChangeOfColor;}
            blenderSection[i].setColor(new Color(r, g, b, 1f));
            setBounds(blenderSection[i].getX(), blenderSection[i].getY(), blenderSection[i].getWidth(), blenderSection[i].getHeight());
        }
    }

    private void changeColor(float delta){
        this.x += delta;
        if(x >= 1/speedChangeOfColor){
            for(int i = 0; i < amountOfSections; i++){
                float r = blenderSection[i].getColor().r;
                float g = blenderSection[i].getColor().g;
                float b = blenderSection[i].getColor().b;
                if(r >= 1f){this.changeR[i] = false; this.changeG[i] = true; this.changeB[i] = false; r = 1f; g = 0f; b = 0f;}
                if(g >= 1f){this.changeR[i] = false; this.changeG[i] = false; this.changeB[i] = true; r = 0f; g = 1f; b = 0f;}
                if(b >= 1f){this.changeR[i] = true; this.changeG[i] = false; this.changeB[i] = false; r = 0f; g = 0f; b = 1f;}
                if(changeR[i]){b -= 0.02; r += 0.02;}
                if(changeG[i]){r -= 0.02; g += 0.02;}
                if(changeB[i]){g -= 0.02; b += 0.02;}
                blenderSection[i].setColor(new Color(r, g, b, 1f));
            }
            this.x = 0;
        }

    }

    //применяем триготометрические формулы для расчета координат box2d секций
    private Vector2 getCoordinates(float angle){
        float radius = blenderSection[0].getHeight()/2 - blenderSection[0].getHeight()/30;
        float x = (Gdx.graphics.getWidth() / 2)/Assets.PPM + radius*MathUtils.cos((angle + 90)*MathUtils.degreesToRadians);
        float y = (Gdx.graphics.getHeight() / 2)/Assets.PPM + radius*MathUtils.sin((angle + 90)*MathUtils.degreesToRadians);
        return new Vector2(x,y);
    }

    public void makeBody(World world){
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(blenderSection[0].getX() + blenderSection[0].getWidth()/2 , blenderSection[0].getY() + blenderSection[0].getHeight() - blenderSection[0].getHeight()/30);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(blenderSection[0].getWidth()/6, blenderSection[0].getHeight()/30);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape ;
        fixtureDef.friction = 1.2f;
        fixtureDef.restitution = 0;
        for(int i = 0; i < amountOfSections; i++) {
            body[i] = world.createBody(bodyDef);
            body[i].createFixture(fixtureDef);
        }
    }

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        this.setSize((Gdx.graphics.getHeight() * percent / 100) / Assets.PPM, (Gdx.graphics.getHeight() * 4 * percent / 100) / Assets.PPM);
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

    public void setRotation(float delta){
        angleOfRotation += delta*speedOfRotation;
        for(int i = 0; i < amountOfSections; i++) {
            this.blenderSection[i].setRotation(angleOfRotation + i*9); // крутим секции видимой конструкции
            this.body[i].setTransform(this.getCoordinates(angleOfRotation + i*9), MathUtils.degreesToRadians*(angleOfRotation + i*9)); //крутим секции box2d
        }
    }

    @Override
    public void draw(Batch batch, float alpha){
        for(int i = 0; i < amountOfSections; i++)
        this.blenderSection[i].draw(batch);
    }

    @Override
    public void act(float delta) {
        this.setRotation(delta);
        this.changeColor(delta);
        super.act(delta);
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
