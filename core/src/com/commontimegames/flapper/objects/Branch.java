package com.commontimegames.flapper.objects;

import com.badlogic.gdx.physics.box2d.World;
import com.commontimegames.flapper.core.Constants;
import com.commontimegames.flapper.core.GameObject;
import com.commontimegames.flapper.core.ProceduralContentQueue;

import java.util.Random;

/**
 * Created by c14726 on 5/3/14.
 */
public class Branch extends GameObject implements ProceduralContentQueue.ProceduralContent{

    private static final Random random = new Random();
    private static final float SQUIRREL_OFFSET = 10f;

    private BranchType branchType;
    private World world;
    private Wall theWall;


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
        switch (branchType){
            case Left:
                add(new Squirrel(Squirrel.SquirrelType.Left, world, 0, 0),
                        -1 * SQUIRREL_OFFSET, 0);
                break;
            case Right:
                add(new Squirrel(Squirrel.SquirrelType.Right, world, 0, 0),
                        SQUIRREL_OFFSET, 0);
                break;
            case Center:
                add(new Squirrel(Squirrel.SquirrelType.Center, world, 0, 0));
                break;
            case Double:
                add(new Squirrel(Squirrel.SquirrelType.Left, world, 0, 0),
                        -1 * SQUIRREL_OFFSET, 0);
                add(new Squirrel(Squirrel.SquirrelType.Right, world, 0, 0),
                        SQUIRREL_OFFSET, 0);
                break;
        }
        theWall = new Wall(world, positionX, positionY, 2.25f, Constants.CONTENT_OFFSET);
        theWall.getBody().setActive(false);
        add(theWall);
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
        return positionY < -1 * Constants.CONTENT_OFFSET;
    }

    @Override
    public void scroll(float amount){
       setPositionY(positionY - amount);
       //Gdx.app.log("Branch", "Scrolled to : " + positionY);
    }


    public void reset(){
        destroy();
        branchType = getNewBranchType();
        //Gdx.app.log("Branch", "Branch type changed to: " + branchType);
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

}
