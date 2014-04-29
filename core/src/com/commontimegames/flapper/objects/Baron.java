package com.commontimegames.flapper.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.commontimegames.flapper.core.RigidBody;

/**
 * Created by c14726 on 4/28/14.
 */
public class Baron extends RigidBody {

    public Baron(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100, 300);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(30f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = body.createFixture(fixtureDef);
        circle.dispose();
    }

    public void draw(SpriteBatch batch) {}
}
