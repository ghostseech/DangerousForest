package com.ghstsch.dangerousforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ghstsch.dangerousforest.players.PlayerStats;
import com.ghstsch.dangerousforest.worlds.WorldController;

import java.util.Vector;

/**
 * Created by Aaa on 24.06.2015.
 */
public class ResourseManager {
    private Vector<BitmapFont> fonts;
    private Color uiColor;
    private Color uiTextColor;
    private Color uiLabelColor;
    private WorldController currentController;
    private PlayerStats stats;

    public void init() {
        fonts = new Vector<BitmapFont>();
        uiColor = new Color(0.1f, 0.6f, 0.8f, 1.0f);
        uiTextColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
        uiLabelColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
        fonts.add(new BitmapFont(Gdx.files.internal("fonts/font_8.fnt"), Gdx.files.internal("fonts/font_8_0.png"), true, true));
        stats = new PlayerStats();

    }

    public void setWorldController(WorldController controller) {
        currentController = controller;
    }

    public WorldController getCurrentController() {
        return currentController;
    }

    public Color getUiColor() {
        return uiColor;
    }

    public Color getUiTextColor() {
        return uiTextColor;
    }

    public Color getUiLabelColor() {
        return uiLabelColor;
    }
    public BitmapFont getFont(int id) {
        return fonts.get(id);
    }
    public PlayerStats getStats() {
        return stats;
    }
}
