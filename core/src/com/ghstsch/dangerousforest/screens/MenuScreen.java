package com.ghstsch.dangerousforest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ghstsch.dangerousforest.ResourseManager;
import com.ghstsch.dangerousforest.ScreenManager;
import com.ghstsch.dangerousforest.ui.UiButton;
import com.ghstsch.dangerousforest.ui.UiElement;
import com.ghstsch.dangerousforest.ui.UiLabel;
import com.ghstsch.dangerousforest.ui.UiProcessor;
import com.ghstsch.dangerousforest.worlds.ForestWorldController;

import java.util.Vector;

/**
 * Created by Aaa on 24.06.2015.
 */
public class MenuScreen extends Screen {
    private UiProcessor uiProcessor;

    private Vector<UiElement> startScreen;
    private Vector<UiElement> worldSelectionScreen;

    private BitmapFont bigFont;
    private SpriteBatch batch;
    private OrthographicCamera cam;

    private UiButton startGameButton;
    private UiButton exitGameButton;

    private UiLabel logoLabel;

    private UiButton backButton;
    private UiButton startForestButton;


    public MenuScreen(ScreenManager screenManager, ResourseManager resourseManager) {
        super(screenManager, resourseManager);
    }

    @Override
    public void init() {
        bigFont = resourseManager.getFont(0);

        startGameButton = new UiButton(200.0f, 300.0f, 800.0f, 140.0f, "START GAME", UiButton.standard, resourseManager.getUiColor(), resourseManager.getUiTextColor(), bigFont);
        exitGameButton = new UiButton(200.0f, 500.0f, 800.0f, 140.0f, "EXIT", UiButton.standard, resourseManager.getUiColor(), resourseManager.getUiTextColor(), bigFont);

        logoLabel = new UiLabel("DANGEROUS FOREST", 1.0f, 100.0f, 100.0f, resourseManager.getUiLabelColor(), bigFont);

        backButton = new UiButton(200.0f, 600.0f, 800.0f, 140.0f, "BACK", UiButton.standard, resourseManager.getUiColor(), resourseManager.getUiTextColor(), bigFont);
        startForestButton = new UiButton(200.0f, 300.0f, 800.0f, 140.0f, "FOREST", UiButton.standard, resourseManager.getUiColor(), resourseManager.getUiTextColor(), bigFont);

        startScreen = new Vector<UiElement>();
        startScreen.add(startGameButton);
        startScreen.add(exitGameButton);
        startScreen.add(logoLabel);

        worldSelectionScreen = new Vector<UiElement>();
        worldSelectionScreen.add(logoLabel);
        worldSelectionScreen.add(startForestButton);
        worldSelectionScreen.add(backButton);

        uiProcessor = new UiProcessor();
        uiProcessor.setUi(startScreen);

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 1920, 1080);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void draw() {
        cam.update();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        batch.begin();
        uiProcessor.draw(batch);
        batch.end();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void handleInput() {
        uiProcessor.update();
        Vector<UiButton> clickedList = uiProcessor.getClickedButtonList();

        for(int i = 0; i < clickedList.size(); i++) {
            UiButton button = clickedList.get(i);
            if(button == startGameButton) {
                uiProcessor.setUi(worldSelectionScreen);
            }
            else if(button == exitGameButton) {
                Gdx.app.exit();
            }
            else if(button == backButton) {
                uiProcessor.setUi(startScreen);
            }
            else if(button == startForestButton) {
                resourseManager.setWorldController(new ForestWorldController());
                screenManager.setScreen(ScreenManager.GAME_SCREEN, true);
                resourseManager.getStats().addStat("SPEED");
                resourseManager.getStats().addStat("POISON");
                resourseManager.getStats().addStat("HEALTH");
                resourseManager.getStats().addStat("ARMOR");
                resourseManager.getStats().addStat("DIGESTION");
            }
        }
    }

    @Override
    public void dispose() {

    }
}
