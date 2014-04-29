package com.commontimegames.flapper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.commontimegames.flapper.core.RigidBody;

/**
 * Created by c14726 on 4/28/14.
 */
public class Ground extends RigidBody{

    public Ground(World world){
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));

        body = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();

        groundBox.setAsBox(Gdx.graphics.getWidth(), 10.0f);
        body.createFixture(groundBox, 0.0f);

        groundBox.dispose();
    }


    @Override
    public void draw(SpriteBatch batch) {

    }
}
