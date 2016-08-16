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


public class BlenderActor extends Actor {
    public static final float size = 40;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Body[] body;
    private Sprite[] blenderSection; //массив хранящий спрайты секций конструкции
    private float angleOfRotation; // угол поворота всей конструкции в градусах хуельсия
    private float speedOfRotation; // скорость поворота всей конструкции
    private int amountOfSections; // количество секций конструкции
    private float stepChangeOfColor; //шаг изменения цвета
    private float speedChangeOfColor; //скорость изменения цвета
    private float colorThreshold; // насыщенность цвета в диапазоне(0.3f - 1f)
    private World world;
    boolean[] changeR; boolean[] changeG; boolean[] changeB;
    float x; // кто так программирует? бла бла бла

    public BlenderActor(World world){
        this.world = world;
        setStepChangeOfColor(12f);
        setSpeedChangeOfColor(70f);
        colorThreshold = 0.9f;
        buildCarcass(); // собираем конструкцию
        refreshPosition();
        setSpeedOfRotation(50f);
        angleOfRotation = MathUtils.random(0 , 360);
        changeColorThreshold(0.3f);
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
    private void changeColorThreshold(float colorThreshold){
        if(colorThreshold > 1f) colorThreshold = 1f;
        if(colorThreshold < 0.3f) colorThreshold = 0.3f;
        for(int i = 0; i < amountOfSections; i++){
            float r = blenderSection[i].getColor().r;
            float g = blenderSection[i].getColor().g;
            float b = blenderSection[i].getColor().b;
            r *= (colorThreshold / this.colorThreshold); g *= (colorThreshold / this.colorThreshold); b *= (colorThreshold / this.colorThreshold);
            this.blenderSection[i].setColor(new Color(r, g, b, 1f));
        }
        this.colorThreshold = colorThreshold;
    }

    private void buildCarcass(){
        setAmountOfSections(MathUtils.random(25 , 34)); // устанавливаем количество секций в конструкции
        body = new Body[amountOfSections];
        blenderSection = new Sprite[amountOfSections];
        changeR = new boolean[amountOfSections];
        changeG = new boolean[amountOfSections];
        changeB = new boolean[amountOfSections];
        float r = 0f; float g = colorThreshold; float b = colorThreshold;
        boolean changeR = false;boolean changeG = true;boolean changeB = false;
        for(int i = 0; i < amountOfSections; i++){
            blenderSection[i] = new Sprite(Assets.manager.get(Assets.blenderSection, Texture.class));
            if(r <= 0f){changeR = false; changeG = true; changeB = false; r = 0f; g = colorThreshold; b = colorThreshold;
                for(int j = i; j < amountOfSections; j++){this.changeR[j] = false; this.changeG[j] = true; this.changeB[j] = false;}
            }
            if(g <= 0f){changeR = false; changeG = false; changeB = true; r = colorThreshold; g = 0f; b = colorThreshold;
                for(int j = i; j < amountOfSections; j++){this.changeR[j] = false; this.changeG[j] = false; this.changeB[j] = true;}
            }
            if(b <= 0f){changeR = true; changeG = false; changeB = false; r = colorThreshold; g = colorThreshold; b = 0f;
                for(int j = i; j < amountOfSections; j++){this.changeR[j] = true; this.changeG[j] = false; this.changeB[j] = false;}
            }
            if(changeR){b += (colorThreshold / stepChangeOfColor); r -= (colorThreshold / stepChangeOfColor);}else{
            if(changeG){r += (colorThreshold / stepChangeOfColor); g -= (colorThreshold / stepChangeOfColor);}else{
            if(changeB){g += (colorThreshold / stepChangeOfColor); b -= (colorThreshold / stepChangeOfColor);}}}
            blenderSection[i].setColor(new Color(r, g, b, 1f));
            setBounds(blenderSection[i].getX(), blenderSection[i].getY(), blenderSection[i].getWidth(), blenderSection[i].getHeight());
        }
        setSize(this.size); // устанавливаем размер
        makeBody();
    }

    private void makeBody(){
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
            body[i] = this.world.createBody(bodyDef);
            body[i].createFixture(fixtureDef);
        }
    }

    public void destroyCarcass(){
        for(int i = 0; i < amountOfSections; i++) {
            this.world.destroyBody(body[i]);
        }
    }

    private void changeColor(float delta){
        this.x += delta;

        if(x >= 1/speedChangeOfColor){
            if(this.colorThreshold <= 1f){
                changeColorThreshold(this.colorThreshold + 0.01f);
            }
            for(int i = 0; i < amountOfSections; i++){
                float r = blenderSection[i].getColor().r;
                float g = blenderSection[i].getColor().g;
                float b = blenderSection[i].getColor().b;
                if(changeR[i]){b += colorThreshold*0.02f; r -= colorThreshold*0.02f;}else{
                if(changeG[i]){r += colorThreshold*0.02f; g -= colorThreshold*0.02f;}else{
                if(changeB[i]){g += colorThreshold*0.02f; b -= colorThreshold*0.02f;}}}
                if((r <= 0f)){this.changeR[i] = false; this.changeG[i] = true; this.changeB[i] = false; r = 0f; g = colorThreshold; b = colorThreshold;}else{
                if((g <= 0f)){this.changeR[i] = false; this.changeG[i] = false; this.changeB[i] = true; r = colorThreshold; g = 0f; b = colorThreshold;}else{
                if((b <= 0f)){this.changeR[i] = true; this.changeG[i] = false; this.changeB[i] = false; r = colorThreshold; g = colorThreshold; b = 0f;}}}
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

    //размер задается в % от высоты дисплея
    public void setSize(float sizeInPercent){
        this.setSize((Gdx.graphics.getHeight() * sizeInPercent / 400) / Assets.PPM, (Gdx.graphics.getHeight() * sizeInPercent / 100) / Assets.PPM);
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

    public void refresh(){
        destroyCarcass(); // разрушаем конструкцию
        buildCarcass(); // собираем конструкцию
        refreshPosition();
        changeColorThreshold(0.3f);
        angleOfRotation = MathUtils.random(0 , 360);
    }
}
