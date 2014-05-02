package com.commontimegames.flapper.core;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by c14726 on 4/28/14.
 */
public abstract class RigidBody extends GameObject {

    protected Body body;

    public void update(){
        /*
           Synchronize Box2D position
           and rotation with sprite
           position/rotation.
         */
        if(sprite != null
             && body != null){
            Vector2 position = body.getPosition();

            this.sprite.setPosition(position.x * Constants.WORLD_TO_SCREEN,
                    position.y * Constants.WORLD_TO_SCREEN);

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

    public void destroy(){
        /* Remove body from Box2D world
           when we're done.
         */
        if(body != null){
            body.getWorld().destroyBody(body);
            body = null;
        }
    }
}
