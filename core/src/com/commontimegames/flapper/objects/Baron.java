package com.commontimegames.flapper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.commontimegames.flapper.core.Constants;
import com.commontimegames.flapper.core.RigidBody;

/**
 * Created by c14726 on 4/28/14.
 */
public class Baron extends RigidBody {

    public static final float FLAP_FORCE = 100;

    public Baron(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(Constants.WORLD_CENTER_X, Constants.WORLD_CENTER_Y);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.4f;

        Fixture fixture = body.createFixture(fixtureDef);
        circle.dispose();
    }

    public void update(){
        //Gdx.app.log("Baron", "Vel x: "
         //           + body.getLinearVelocity().x
         //               + ", Vel y: " + body.getLinearVelocity().y
         //                   + ", mag: " + body.getLinearVelocity().len());
    }
    public void flap(float screenX, float screenY){

        float worldX = screenX * Constants.SCREEN_TO_WORLD;
        float worldY = Constants.WORLD_HEIGHT - (screenY * Constants.SCREEN_TO_WORLD);

        Vector2 pos = body.getPosition();
        Vector2 dir = new Vector2(worldX-pos.x, worldY-pos.y).nor().scl(FLAP_FORCE);

        body.setLinearVelocity(0,0);
        body.applyLinearImpulse(dir.x,
                                dir.y,
                                pos.x,
                                pos.y,
                                true);

        Gdx.app.log("Baron",
                "Baron.flap(" + screenX + "," + screenY +
                        ") -> (" + worldX + "," + worldY + ")");

    }
    public void draw(SpriteBatch batch) {}

    public static enum FlapperState{
        Normal,
        Flapping,
        Spinning,
        Dead
    }
}
