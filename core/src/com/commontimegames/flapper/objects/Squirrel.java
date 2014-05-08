package com.commontimegames.flapper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.commontimegames.flapper.core.Constants;
import com.commontimegames.flapper.core.GameObject;
import com.commontimegames.flapper.core.RigidBody;

/**
 * Created by c14726 on 5/6/14.
 */
public class Squirrel extends RigidBody implements RigidBody.Collider{

    private SquirrelType type;
    private SquirrelState state;
    private GameObject nut;

    public Squirrel(SquirrelType type,
                    World world,
                    float positionX,
                    float positionY){
        super(positionX, positionY);
        Gdx.app.log("Squirrel", "Squirrel position created at: " + positionX + ", " + positionY);
        this.type = type;
        this.state = SquirrelState.Idle;

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(positionX, positionY);

        body = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();

        groundBox.setAsBox(2, 2);
        body.createFixture(groundBox, 0.0f);
        body.setUserData(this);

        groundBox.dispose();
    }

    @Override
    public void onHit(GameObject g) {
        if(g instanceof Baron){
            state = SquirrelState.Dead;
            body.setType(BodyDef.BodyType.DynamicBody);
            body.applyLinearImpulse(0,
                                    Constants.SQUIRREL_DEATH_IMPULSE,
                                    positionX,
                                    positionY,
                                    true);
        } else if(Constants.GROUND_NAME.equalsIgnoreCase(g.getName())){
            if(state == SquirrelState.Dead){
                destroy();
            }
        }
    }

    public void destroy(){
        super.destroy();
        Gdx.app.log("Squirrel", "Squirrel got destroyed!");
    }

    public enum SquirrelType{
        Left,
        Right,
        Center
    }

    public enum SquirrelState{
        Idle,
        Throwing,
        Dead
    }

}
