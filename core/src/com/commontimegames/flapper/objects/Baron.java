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

    public static final float FLAP_FORCE = 10000f;

    public Baron(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100, 300);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(30f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.4f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.7f;

        Fixture fixture = body.createFixture(fixtureDef);
        circle.dispose();
    }

    public void flap(float x, float y){
        Gdx.app.log("Baron", "Baron.flap()");
        Vector2 pos = body.getPosition();
        body.applyLinearImpulse(FLAP_FORCE * (x - Gdx.graphics.getWidth()/2f),
                                FLAP_FORCE * y,
                                pos.x,
                                pos.y,
                                true);
    }
    public void draw(SpriteBatch batch) {}
}
