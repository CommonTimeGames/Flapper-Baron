package com.commontimegames.flapper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.commontimegames.flapper.core.RigidBody;

/**
 * Created by c14726 on 5/3/14.
 */
public class Branch extends RigidBody{

    private static final float SQUIRREL_OFFSET = 5f;

    private BranchType branchType;

    public Branch(BranchType branchType,
                  World world,
                  float positionX,
                  float positionY){
        super(positionX, positionY);

        this.branchType = branchType;

        if(branchType == BranchType.Left){
            init(world);
        }
    }

    public void init(World world){
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(positionX-SQUIRREL_OFFSET, positionY);

        body = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();

        groundBox.setAsBox(1, 1);
        body.createFixture(groundBox, 0.0f);
        body.setUserData(this);

        groundBox.dispose();
    }
    @Override
    public void draw(SpriteBatch batch) {

    }

    public void update(){
        super.update();
    }

    public BranchType getBranchType() {
        return branchType;
    }

    public void setBranchType(BranchType branchType) {
        this.branchType = branchType;
    }

    public static enum BranchType{
       None,
       Left,
       Right,
       Double
    }

    @Override
    public boolean isOffscreen() {
        return positionY < 0;
    }

    @Override
    public void scroll(float amount){
       setPositionY(positionY - amount);

        if(branchType == BranchType.Left)
        Gdx.app.log("Branch: ", "scroll() -> pos x " + positionX + ", pos y: " + positionY);
    }

    public void setPositionY(float y){
        positionY = y;
        if(body != null){
            Transform curPos = body.getTransform();
            body.setTransform(positionX-SQUIRREL_OFFSET,
                    positionY,
                    curPos.getRotation());
        }

    }

    public void setPositionX(float x){
        positionX = x;
        if(body != null){
            Transform curPos = body.getTransform();
            body.setTransform(positionX-SQUIRREL_OFFSET,
                    positionY,
                    curPos.getRotation());
        }
    }

    public void reset(){
        /* if(branchType == BranchType.None){
            return;
        }

        World world = body.getWorld();

        if(branchType == BranchType.Left
            || branchType == BranchType.Right){
            destroy();
            init(world);
        } else if (branchType == BranchType.Double){
            //Do more stuff.
        } */
    }
}
