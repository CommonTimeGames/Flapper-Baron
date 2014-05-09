package com.commontimegames.flapper.core;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;

/**
 * Created by c14726 on 4/28/14.
 */
public abstract class RigidBody extends GameObject {

    protected Body body;

    public RigidBody(float positionX, float positionY){
        super(positionX, positionY);
    }

    public void update(){

        if(body != null){
            positionX = body.getPosition().x;
            positionY = body.getPosition().y;
        }

        /*
           Synchronize Box2D position
           and rotation with sprite
           position/rotation.
         */
        if(sprite != null
             && body != null){
            /* Synchronize Rigidbody and Sprite positions */
            this.sprite.setPosition(positionX, positionY);
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

    public void setPositionY(float y){
        super.setPositionY(y);
        syncTransform();

    }

    public void setPositionX(float x){
        super.setPositionX(x);
        syncTransform();
    }

    protected void syncTransform() {
        if(body != null){
            Transform curPos = body.getTransform();
            body.setTransform(positionX,
                              positionY,
                              curPos.getRotation());
        }
    }

    public void destroy(){
        /* Remove body from Box2D world
           when we're done.
         */
        super.destroy();
        if(body != null){
            body.getWorld().destroyBody(body);
            body = null;
        }
    }

    public boolean isFalling(){
        return body != null && body.getLinearVelocity().y <= 0;
    }

    public interface Collider{
        public void onHit(GameObject g);
    }



}
