package com.ghstsch.dangerousforest.players;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ghstsch.dangerousforest.InputHandler;
import com.ghstsch.dangerousforest.objects.*;

/**
 * Created by Aaa on 24.06.2015.
 */
public abstract class Player extends PhysicalObject {
    protected boolean dead;
    protected Vector2 moveDirection;
    protected float speed;
    protected PlayerStats stats;

    public Player(float x, float y, float angle, World world) {
        super(x, y, angle, world);
        dead = false;
        moveDirection = new Vector2();
        speed = 600.0f;
    }

    public abstract void setStats(PlayerStats stats);

    @Override
    public boolean isDead() {
        return dead;
    }
}
