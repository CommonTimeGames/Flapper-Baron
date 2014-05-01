package com.commontimegames.flapper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.commontimegames.flapper.core.RigidBody;

/**
 * Created by c14726 on 4/28/14.
 */
public class Baron extends RigidBody {

    public static final float FLAP_FORCE = (float)Math.pow(10,20);

    public Baron(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100, 300);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(30f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.0001f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 1f;

        Fixture fixture = body.createFixture(fixtureDef);
        circle.dispose();
    }

    public void update(){
        Gdx.app.log("Baron", "Vel x: "
                    + body.getLinearVelocity().x
                        + ", Vel y: " + body.getLinearVelocity().y
                            + ", mag: " + body.getLinearVelocity().len());
    }
    public void flap(float x, float y){
        y = Gdx.graphics.getHeight() - y;

        Vector2 pos = body.getPosition();
        Vector2 dir = new Vector2(x-pos.x, y-pos.y).nor().scl(FLAP_FORCE);
        //Vector2 dir = new Vector2(x-pos.x, y-pos.y).scl(500);

        body.applyLinearImpulse(dir.x,
                                dir.y,
                                pos.x,
                                pos.y,
                                true);

        Gdx.app.log("Baron", "Baron.flap(" + x + "," + y +") - "
                    + pos.x + ", " + pos.y);

    }
    public void draw(SpriteBatch batch) {}

    public static enum FlapperState{
        Normal,
        Flapping,
        Spinning,
        Dead
    }
}
