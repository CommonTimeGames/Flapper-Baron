package com.commontimegames.flapper.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.commontimegames.flapper.core.Constants;
import com.commontimegames.flapper.core.RigidBody;

/**
 * Created by c14726 on 5/1/14.
 */
public class Wall extends RigidBody {

    public Wall(World world,
                float posX, float posY,
                float width, float height){

        super(posX, posY);

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(positionX, positionY);

        body = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();

        groundBox.setAsBox(width, height);
        body.createFixture(groundBox, 0.0f);
        body.setUserData(this);

        groundBox.dispose();
    }

    public void draw(SpriteBatch batch){}
}
