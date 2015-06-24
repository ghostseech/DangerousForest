package com.ghstsch.dangerousforest.worlds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ghstsch.dangerousforest.PlayerStats;
import com.ghstsch.dangerousforest.objects.GameObject;
import com.ghstsch.dangerousforest.objects.PhysicalObject;
import com.ghstsch.dangerousforest.objects.Player;

import java.util.Vector;

/**
 * Created by Aaa on 24.06.2015.
 */
public abstract class WorldController {
    protected World world;
    protected Vector<GameObject> objectList;
    protected Player player;
    protected boolean ended;
    protected int day;
    protected int finalDay;

    public WorldController() {
        ended = false;

        world = new World(new Vector2(0, 0), true);

        objectList = new Vector<GameObject>();
        generateWorld(1);
    }

    public abstract void update(float dt);

    public abstract void generateWorld(int day);

    protected void resolveCollisions() {
        Array<Contact> contactArray = world.getContactList();
        for(int i = 0; i < world.getContactList().size; i++) {
            if(contactArray.get(i).isTouching()) {
                PhysicalObject objectA = (PhysicalObject)contactArray.get(i).getFixtureA().getBody().getUserData();
                PhysicalObject objectB = (PhysicalObject)contactArray.get(i).getFixtureB().getBody().getUserData();

                objectA.resolveCollision(objectB);
                objectB.resolveCollision(objectA);
                contactArray.get(i).setEnabled(false);
                contactArray.get(i).resetFriction();
            }
        }
    }

    protected void removeDeadObjects() {
        for(int i = 0; i < objectList.size(); i++) {
            if(objectList.get(i).isDead()) {
                objectList.get(i).dispose();
                objectList.remove(i);
                i--;
            }
        }
    }

    public void setPlayerStats(PlayerStats stats) {
        player.setStats(stats);
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public Vector<GameObject> getObjectList() {
        return objectList;
    }

    public int getFinalDay() {
        return finalDay;
    }

    public int getCurrentDay() {
        return day;
    }

    public boolean isGameEnded() {
        return ended;
    }

    public void endGame() {
        ended = true;
    }

    public void continueGame() {
        ended = false;
    }
    public void disposeObjects() {
        for(int i = 0; i < objectList.size(); i++)objectList.get(i).dispose();
        objectList.clear();
    }
}
