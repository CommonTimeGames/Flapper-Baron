package com.commontimegames.flapper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.commontimegames.flapper.core.Constants;
import com.commontimegames.flapper.core.GameObject;
import com.commontimegames.flapper.core.RigidBody;

/**
 * Created by c14726 on 4/28/14.
 */
public class Baron extends RigidBody implements RigidBody.Collider{

    public static final float FLAP_FORCE = 100;

    private BaronState state;

    public Baron(World world){
        super(Constants.WORLD_CENTER_X, Constants.WORLD_CENTER_Y);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX, positionY);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.2f;

        Fixture fixture = body.createFixture(fixtureDef);
        body.setUserData(this);

        circle.dispose();
    }

    public void update(){
        super.update();

       // Gdx.app.log("Baron", "Pos x: " + positionX
        //                + " Pos y: " + positionY);

       if(state == BaronState.Flapping
               && body.getLinearVelocity().y < 0){
           state = BaronState.Normal;
           Gdx.app.log("Baron", "Done flapping");
       }
    }
    public void flap(float screenX, float screenY){

        if(state == BaronState.Dead){
            return;
        }

        state = BaronState.Flapping;

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

        //Gdx.app.log("Baron",
        //        "Baron.flap(" + screenX + "," + screenY +
        //                ") -> (" + worldX + "," + worldY + ")");

    }
    public void draw(SpriteBatch batch) {}

    public BaronState getState() {
        return state;
    }

    public void setState(BaronState state) {
        this.state = state;
        if (this.state == BaronState.Dead) {
            Gdx.app.log("Baron", "The baron is dead!");
        }
    }

    public static enum BaronState {
        Normal,
        Flapping,
        Spinning,
        Dead
    }

    @Override
    public void onHit(GameObject g) {
        if(g instanceof Squirrel
                || Constants.GROUND_NAME.equalsIgnoreCase(g.getName())){
            setState(BaronState.Dead);
        }
    }
}
