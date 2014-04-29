package com.commontimegames.flapper.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by c14726 on 4/28/14.
 */
public abstract class GameObject {
    protected Sprite sprite;

    public abstract void update();
    public abstract void draw(SpriteBatch batch);

    public Sprite getSprite() {
        return sprite;
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
