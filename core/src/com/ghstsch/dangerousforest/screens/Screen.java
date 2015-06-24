package com.ghstsch.dangerousforest.screens;

import com.ghstsch.dangerousforest.ResourseManager;
import com.ghstsch.dangerousforest.ScreenManager;

/**
 * Created by Aaa on 24.06.2015.
 */
public abstract class Screen {
    protected ScreenManager screenManager;
    protected ResourseManager resourseManager;

    public Screen(ScreenManager screenManager, ResourseManager resourseManager) {
        this.screenManager = screenManager;
        this.resourseManager = resourseManager;
    }

    public abstract void init();
    public abstract void draw();
    public abstract void update(float dt);
    public abstract void handleInput();
    public abstract void dispose();
}
