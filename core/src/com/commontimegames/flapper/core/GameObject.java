package com.commontimegames.flapper.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by c14726 on 4/28/14.
 */
public abstract class GameObject {

    protected Sprite sprite;

    protected float positionX;
    protected float positionY;

    public GameObject(float positionX, float positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public abstract void update();
    public abstract void draw(SpriteBatch batch);

    public Sprite getSprite() { return sprite; }
    public void setSprite(Sprite sprite) { this.sprite = sprite;}

    public void destroy(){}

    /* For recycling procedural objects */
    public boolean isOffscreen(){ return false; }
    public void scroll(float amount){}
    public void reset() {}
    public float width() { return 0; }

    public void setPositionX(float x){
        positionX = x;
    }

    public void setPositionY(float y){
        positionY = y;
    }

    public float getPositionX(){
        return positionX;
    }

    public float getPositionY(){
        return positionY;
    }

}
