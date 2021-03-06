package com.ghstsch.dangerousforest;


import com.ghstsch.dangerousforest.screens.GameScreen;
import com.ghstsch.dangerousforest.screens.MenuScreen;
import com.ghstsch.dangerousforest.screens.Screen;
import com.ghstsch.dangerousforest.screens.UpdateScreen;

public class ScreenManager {
    public static final int GAME_SCREEN = 1;
    public static final int MENU_SCREEN = 2;
    public static final int UPDATE_SCREEN = 3;

    private ResourseManager resourseManager;

    private Screen gameScreen;
    private Screen menuScreen;
    private Screen updateScreen;

    private Screen currentScreen;

    public void setScreen(int screen, boolean recreate) {
        if(screen == GAME_SCREEN) {
            if(recreate || gameScreen == null) {
                gameScreen = new GameScreen(this, resourseManager);
                gameScreen.init();
            }
            currentScreen = gameScreen;
        }
        else if(screen == MENU_SCREEN) {
            if(recreate || menuScreen == null) {
                menuScreen = new MenuScreen(this, resourseManager);
                menuScreen.init();
            }
            currentScreen = menuScreen;
        }
        else if(screen == UPDATE_SCREEN) {
            if(recreate || updateScreen == null) {
                updateScreen = new UpdateScreen(this, resourseManager);
                updateScreen.init();
            }
            currentScreen = updateScreen;
        }
    }

    public void setResourse(ResourseManager resourseManager) {
        this.resourseManager = resourseManager;
    }

    public void draw() {
        currentScreen.draw();
    }

    public void update(float dt) {
        currentScreen.update(dt);
    }
}
