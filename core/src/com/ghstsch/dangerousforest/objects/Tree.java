package com.ghstsch.dangerousforest.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Aaa on 24.06.2015.
 */
public class Tree extends PhysicalObject {
    float size;
    static Texture texture = new Texture(Gdx.files.internal("textures/tree_1.png"), true);
    public Tree(float x, float y, float angle, float size, World world) {
        super(x, y, angle, world);
        this.size = size;
        createShape(x, y, angle);
    }

    @Override
    public void createShape(float x, float y, float angle) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(size);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.7f;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
        body.setTransform(x, y, angle);
        body.setUserData(this);
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x - size, body.getPosition().y - size, body.getPosition().x, body.getPosition().y, size * 2, size * 2, 1.0f, 1.0f, body.getAngle() * 0.017f, 1, 1, 32, 32, false, false);
    }

    @Override
    public void resolveCollision(PhysicalObject object) {

    }

    @Override
    public void dispose() {
        world.destroyBody(body);
    }
}
