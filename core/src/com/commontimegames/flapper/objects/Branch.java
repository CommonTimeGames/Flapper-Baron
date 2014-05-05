package com.commontimegames.flapper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.commontimegames.flapper.core.RigidBody;

import java.util.Random;

/**
 * Created by c14726 on 5/3/14.
 */
public class Branch extends RigidBody{

    private static final Random random = new Random();
    private static final float SQUIRREL_OFFSET = 10f;

    private BranchType branchType;
    private World world;

    public Branch(World world,
                  float positionX,
                  float positionY){
        this(getNewBranchType(),
                world,
                positionX,
                positionY);
    }

    public Branch(BranchType branchType,
                  World world,
                  float positionX,
                  float positionY){
        super(positionX, positionY);
        this.world = world;
        this.branchType = branchType;
        init(world);
    }

    public void init(World world){
        if(branchType == BranchType.None){
            return;
        }

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(positionX + getOffsetForBranchType(branchType), positionY);

        body = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();

        groundBox.setAsBox(1, 2);
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
       Center,
       Double
    }

    @Override
    public boolean isOffscreen() {
        return positionY < 0;
    }

    @Override
    public void scroll(float amount){
       setPositionY(positionY - amount);
    }

    public void setPositionY(float y){
        positionY = y;
        if(body != null){
            Transform curPos = body.getTransform();
            body.setTransform(
                    positionX + getOffsetForBranchType(branchType),
                    positionY,
                    curPos.getRotation());
        }

    }

    public void setPositionX(float x){
        positionX = x;
        if(body != null){
            Transform curPos = body.getTransform();
            body.setTransform(
                    positionX + getOffsetForBranchType(branchType),
                    positionY,
                    curPos.getRotation());
        }
    }

    public void reset(){
        destroy();
        branchType = getNewBranchType();
        Gdx.app.log("Branch", "Branch type changed to: " + branchType);
        init(world);
    }

    public static BranchType getNewBranchType(){
        int rand = random.nextInt(101);

        if(rand <= 50){
            return BranchType.None;
        } else if(rand <= 63 ){
            return BranchType.Left;
        } else if(rand <= 78){
            return BranchType.Right;
        } else if(rand <= 88){
            return BranchType.Center;
        } else {
            return BranchType.Double;
        }
    }

    public static float getOffsetForBranchType(BranchType type){
        switch(type){
            case Center:
            case None:
            case Double:
                return 0;
            case Left:
                return -1 * SQUIRREL_OFFSET;
            case Right:
                return SQUIRREL_OFFSET;
            default:
                return 0;
        }
    }
}
