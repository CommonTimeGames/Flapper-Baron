package com.commontimegames.flapper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.commontimegames.flapper.core.RigidBody;
import com.commontimegames.flapper.objects.Baron;
import com.commontimegames.flapper.objects.Ground;

/**
 * Created by c14726 on 4/29/14.
 */
public class GameScreen implements Screen, InputProcessor{

    SpriteBatch batch;
    Texture img;
    World world;
    Baron baron;
    Ground ground;
    Box2DDebugRenderer debugRenderer;

    public GameScreen(){
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        world = new World(new Vector2(0, -30), true);
        debugRenderer = new Box2DDebugRenderer();
        baron = new Baron(world);
        ground = new Ground(world);
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

        Body body = baron.getBody();
        //body.applyLinearImpulse(100f, 0, body.getPosition().x, body.getPosition().y, true);
        world.step(RigidBody.BOX_STEP_TIME, 6, 2);
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
        Gdx.app.log("GameScreen", "touchDown: x: " + screenX
                           + ", y: " + screenY
                           + " pointer: " + pointer
                           + " button: " + button);
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
}