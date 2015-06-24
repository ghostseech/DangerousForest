package com.ghstsch.dangerousforest.players;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.ghstsch.dangerousforest.InputHandler;
import com.ghstsch.dangerousforest.objects.PhysicalObject;

/**
 * Created by Aaa on 24.06.2015.
 */
public class Bug extends Player {
    public Bug(float x, float y, float angle, World world) {
        super(x, y, angle, world);
        createShape(x, y, angle);
    }
    @Override
    public void createShape(float x, float y, float angle) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(2.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.7f;

        body.setLinearDamping(1.75f);
        body.setAngularDamping(25.0f);
        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
        body.setTransform(x, y, angle);
        body.setUserData(this);
    }

    @Override
    public void update(float dt) {
        moveDirection.set(0.0f, 0.0f);
        if(InputHandler.isKeyDown(InputHandler.A))moveDirection.add(-1.0f,0.0f);
        if(InputHandler.isKeyDown(InputHandler.D))moveDirection.add(1.0f,0.0f);
        if(InputHandler.isKeyDown(InputHandler.W))moveDirection.add(0.0f,-1.0f);
        if(InputHandler.isKeyDown(InputHandler.S))moveDirection.add(0.0f,1.0f);
        float length = (float)Math.sqrt((double)(moveDirection.x * moveDirection.x + moveDirection.y * moveDirection.y));

        if(length != 0.0f) {
            moveDirection.x = moveDirection.x * speed * dt;
            moveDirection.y = moveDirection.y * speed * dt;
        }
        body.applyLinearImpulse(moveDirection.x, moveDirection.y, getPosition().x, getPosition().y, true);
    }

    @Override
    public void draw(SpriteBatch batch) {
        // batch.draw(texture, body.getPosition().x - 30.0f, body.getPosition().y - 30.0f, body.getPosition().x, body.getPosition().y, 60.0f, 60.0f, 1.0f, 1.0f, body.getAngle() * 0.017f, 1, 1, 32, 32, false, false);
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
        speed = 400.0f + 100.0f * this.stats.getStatValue(0);
    }

    @Override
    public void resolveCollision(PhysicalObject object) {

    }

    @Override
    public void dispose() {
        world.destroyBody(body);
    }
}
