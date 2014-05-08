package com.commontimegames.flapper.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by c14726 on 4/28/14.
 */
public abstract class GameObject {

    protected Sprite sprite;

    protected Set<GameObject> children;
    protected GameObject parent;

    protected float positionX;
    protected float positionY;

    private String name;

    protected static Random random = new Random();

    public GameObject(float positionX, float positionY){
        this.positionX = positionX;
        this.positionY = positionY;
        children = new HashSet<GameObject>();
    }

    public void update(){
        for(GameObject g: children){
            g.update();
        }
    }

    public void draw(SpriteBatch batch){
        for(GameObject g: children){
            g.draw(batch);
        }
    }
    public void destroy(){
        for(Iterator<GameObject> i = children.iterator(); i.hasNext();){
            GameObject g = i.next();
            g.destroy();
            g.parent = null;
            i.remove();
        }
    }

    public GameObject getParent(){
        return parent;
    }

    public Sprite getSprite() { return sprite; }
    public void setSprite(Sprite sprite) { this.sprite = sprite;}

    public void add(GameObject g,
                    float localX,
                    float localY){
        g.setPositionX(this.positionX + localX);
        g.setPositionY(this.positionY + localY);
        g.parent = this;
        children.add(g);
    }

    public void add(GameObject g){
        add(g, 0, 0);
    }

    public void remove(GameObject g){
        this.children.remove(g);
        g.parent = null;
    }

    public void setPositionX(float x){
        for(GameObject g: children){
            float delta = g.getPositionX() - positionX;
            g.setPositionX(x + delta);
        }
        positionX = x;
    }

    public void setPositionY(float y){
        for(GameObject g: children){
            float delta = g.getPositionY() - positionY;
            g.setPositionY(y + delta);
        }
        positionY = y;
    }

    public float getPositionX(){
        return positionX;
    }

    public float getPositionY(){
        return positionY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
