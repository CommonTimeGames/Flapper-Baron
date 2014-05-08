package com.commontimegames.flapper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.commontimegames.flapper.core.Constants;
import com.commontimegames.flapper.core.GameObject;
import com.commontimegames.flapper.core.ProceduralContentQueue;
import com.commontimegames.flapper.core.RigidBody;
import com.commontimegames.flapper.objects.Baron;
import com.commontimegames.flapper.objects.Branch;
import com.commontimegames.flapper.objects.Wall;

/**
 * Created by c14726 on 4/29/14.
 */
public class GameScreen implements Screen,
                                   InputProcessor,
                                   ContactListener{

    SpriteBatch batch;
    Texture img;
    World world;
    Baron baron;

    Wall ground;
    Wall leftWall;
    Wall rightWall;
    Wall ceiling;
    ProceduralContentQueue pq;

    Box2DDebugRenderer debugRenderer;

    public GameScreen(){
        batch = new SpriteBatch();
        batch.getProjectionMatrix().scale(Constants.WORLD_TO_SCREEN, Constants.WORLD_TO_SCREEN, 1);
        img = new Texture("badlogic.jpg");
        world = new World(new Vector2(0, -1 * Constants.BOX_GRAVITY), true);
        debugRenderer = new Box2DDebugRenderer();

        baron = new Baron(world);

        ground = new Wall(world,
                          Constants.WORLD_CENTER_X, -6,
                          Constants.WORLD_WIDTH, 1);

        ground.setName(Constants.GROUND_NAME);

        leftWall = new Wall(world,
                            -1, Constants.WORLD_CENTER_Y,
                            1, Constants.WORLD_HEIGHT);

        rightWall = new Wall(world,
                             Constants.WORLD_WIDTH+1, Constants.WORLD_CENTER_Y,
                             1, Constants.WORLD_HEIGHT);

        ceiling = new Wall(world,
                           Constants.WORLD_CENTER_X, Constants.WORLD_HEIGHT+1,
                           Constants.WORLD_WIDTH, 1);

        pq = new ProceduralContentQueue();
        pq.add(new Branch(Branch.BranchType.Double, world, Constants.WORLD_CENTER_X, 1 * Constants.CONTENT_OFFSET));
        pq.add(new Branch(Branch.BranchType.Double, world, Constants.WORLD_CENTER_X, 3f * Constants.CONTENT_OFFSET));
        pq.add(new Branch(Branch.BranchType.Double, world, Constants.WORLD_CENTER_X, 5 * Constants.CONTENT_OFFSET));
        //pq.add(new Branch(Branch.BranchType.Double, world, Constants.WORLD_CENTER_X, 4 * Constants.CONTENT_OFFSET));

        world.setContactListener(this);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //batch.draw(img, 0, 0);
        batch.end();
        debugRenderer.render(world, batch.getProjectionMatrix());

        baron.update();

        pq.scroll();

        //if(baron.getState() == Baron.BaronState.Flapping){
        //    pq.scroll();
        //}

        pq.recycleOffScreen();

        world.step(Constants.BOX_STEP_TIME, 6, 2);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void dispose(){
        ground.destroy();
        baron.destroy();
        world.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.SPACE){
            baron.getBody().setTransform(Constants.WORLD_CENTER_X,Constants.WORLD_CENTER_Y, 0);
            baron.getBody().setLinearVelocity(0f,0f);
            baron.setState(Baron.BaronState.Normal);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //Gdx.app.log("GameScreen", "touchDown: x: " + screenX
        //                   + ", y: " + screenY
        //                   + " pointer: " + pointer
        //                   + " button: " + button);
        baron.flap(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void beginContact(Contact contact) {
        final GameObject a = (GameObject)contact.getFixtureA().getBody().getUserData();
        final GameObject b = (GameObject)contact.getFixtureB().getBody().getUserData();

        /**
         * #beginContact() is called during
         * the Box2D step, so we cannot modify
         * the world during this time. The solution?
         * invoke the callback before the next call to
         * #render(), when the world can be modified.
         */
        if(a instanceof RigidBody.Collider){
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    ((RigidBody.Collider)a).onHit(b);
                }
            });

        }
        if(b instanceof RigidBody.Collider){
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    ((RigidBody.Collider)b).onHit(a);
                }
            });
        }

        Gdx.app.log("GameScreen", "Contact between " + a.getClass() + ", and " + b.getClass());
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
