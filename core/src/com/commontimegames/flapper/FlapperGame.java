package com.commontimegames.flapper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.commontimegames.flapper.core.RigidBody;
import com.commontimegames.flapper.objects.Baron;
import com.commontimegames.flapper.objects.Ground;

public class FlapperGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    World world;
    Baron baron;
    Ground ground;

    private Box2DDebugRenderer debugRenderer;

    public static final float METERS_TO_WORLD = 10;
    public static final float WORLD_TO_METERS = 1 / METERS_TO_WORLD;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        world = new World(new Vector2(0, -30), true);
        debugRenderer = new Box2DDebugRenderer();
        baron = new Baron(world);
        ground = new Ground(world);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		batch.end();
        debugRenderer.render(world, batch.getProjectionMatrix());
        world.step(RigidBody.BOX_STEP_TIME, 6, 2);
	}
}
