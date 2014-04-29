package com.commontimegames.flapper.core;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by c14726 on 4/28/14.
 */
public abstract class RigidBody extends GameObject {

    public static final float BOX_TO_WORLD = 10f;
    public static final float BOX_STEP_TIME = 1/45f;

    protected Body body;

    public void update(){
        /*
           Synchronize Box2D position
           and rotation with sprite
           position/rotation.
         */
        if(sprite != null){
            Vector2 position = body.getPosition();

            this.sprite.setPosition(position.x * BOX_TO_WORLD,
                    position.y * BOX_TO_WORLD);

            this.sprite.setRotation(MathUtils.radiansToDegrees
                    * body.getAngle());
        }
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
