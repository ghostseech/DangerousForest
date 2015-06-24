package com.ghstsch.dangerousforest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ghstsch.dangerousforest.ResourseManager;
import com.ghstsch.dangerousforest.ScreenManager;
import com.ghstsch.dangerousforest.players.Player;
import com.ghstsch.dangerousforest.ui.UiButton;
import com.ghstsch.dangerousforest.ui.UiElement;
import com.ghstsch.dangerousforest.ui.UiLabel;
import com.ghstsch.dangerousforest.ui.UiProcessor;

import java.util.Vector;

/**
 * Created by Aaa on 24.06.2015.
 */
public class UpdateScreen extends Screen {
    private UiProcessor uiProcessor;
    private Vector<UiElement> ui;
    private SpriteBatch batch;
    private OrthographicCamera cam;

    private UiLabel biomassIndicator;

    private int position;
    private Vector<UiButton> UpButtons;
    private UiButton ScrollDown;
    private UiButton ScrollUp;

    private UiButton nextDay;

    public UpdateScreen(ScreenManager screenManager, ResourseManager resourseManager) {
        super(screenManager, resourseManager);
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
    public void init() {

        position = 0;
        UpButtons = new Vector<UiButton>();

        UpButtons.add(new UiButton(
                230.0f, 300.0f,
                700.0f, 120.0f,
                "UP", UiButton.standard,
                resourseManager.getUiColor(), resourseManager.getUiTextColor(),
                resourseManager.getFont(0)));
        UpButtons.add(new UiButton(
                230.0f, 450.0f,
                700.0f, 120.0f,
                "UP", UiButton.standard,
                resourseManager.getUiColor(), resourseManager.getUiTextColor(),
                resourseManager.getFont(0)));
        UpButtons.add(new UiButton(
                230.0f, 600.0f,
                700.0f, 120.0f,
                "UP", UiButton.standard,
                resourseManager.getUiColor(), resourseManager.getUiTextColor(),
                resourseManager.getFont(0)));
        UpButtons.add(new UiButton(
                230.0f, 750.0f,
                700.0f, 120.0f,
                "UP", UiButton.standard,
                resourseManager.getUiColor(), resourseManager.getUiTextColor(),
                resourseManager.getFont(0)));


        ScrollDown = new UiButton(
                230.0f, 900.0f,
                700.0f, 120.0f,
                "NEXT", UiButton.standard,
                resourseManager.getUiColor(), resourseManager.getUiTextColor(),
                resourseManager.getFont(0));

        ScrollUp = new UiButton(
                230.0f, 150.0f,
                700.0f, 120.0f,
                "PREV", UiButton.standard,
                resourseManager.getUiColor(), resourseManager.getUiTextColor(),
                resourseManager.getFont(0));

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 1920, 1080);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        ui = new Vector<UiElement>();

        uiProcessor = new UiProcessor();
        uiProcessor.setUi(ui);



       /* digestionUp = new UiButton(
                630.0f, 750.0f,
                200.0f, 120.0f,
                "BUY", UiButton.standard,
                resourseManager.getUiColor(), resourseManager.getUiTextColor(),
                resourseManager.getFont(0));*/

        nextDay = new UiButton(
                1600.0f, 480.0f,
                240.0f, 120.0f,
                ">", UiButton.standard,
                resourseManager.getUiColor(), resourseManager.getUiTextColor(),
                resourseManager.getFont(0));

        biomassIndicator = new UiLabel("BIOMASS:", 0.3f,
                30.0f, 30.0f,
                resourseManager.getUiTextColor(),
                resourseManager.getFont(0));

        ui.add(nextDay);
        ui.add(biomassIndicator);
        ui.add(UpButtons.get(0));
        ui.add(UpButtons.get(1));
        ui.add(UpButtons.get(2));
        ui.add(UpButtons.get(3));
        ui.add(ScrollDown);
        ui.add(ScrollUp);

    }

    @Override
    public void handleInput() {
        uiProcessor.update();
    }

    @Override
    public void update(float dt) {

        if(resourseManager.getStats().getStatsCount() > 4) {
            for(int i = 0; i < 4; i++) {
                UpButtons.get(i).setText(resourseManager.getStats().getStatName(i + position) + "  " + resourseManager.getStats().getStatValue(i + position));
            }
        }
        handleInput();


        Vector<UiButton> clickedList = uiProcessor.getClickedButtonList();
        for(int i = 0; i < clickedList.size(); i++) {
            if(clickedList.get(i) == nextDay) {
                resourseManager.getCurrentController().generateWorld(resourseManager.getCurrentController().getCurrentDay()+1);
                screenManager.setScreen(ScreenManager.GAME_SCREEN, true);
            }
            else if(clickedList.get(i) == ScrollUp) {
                if(position > 0) position--;
            }
            else if(clickedList.get(i) == ScrollDown) {
                if(position + 4 < resourseManager.getStats().getStatsCount()) position++;
            }
            else if(clickedList.get(i) == UpButtons.get(0)) {
                resourseManager.getStats().addToStat(position, 1.0f);
            }
            else if(clickedList.get(i) == UpButtons.get(1)) {
                resourseManager.getStats().addToStat(position + 1, 1.0f);
            }
            else if(clickedList.get(i) == UpButtons.get(2)) {
                resourseManager.getStats().addToStat(position + 2, 1.0f);
            }
            else if(clickedList.get(i) == UpButtons.get(3)) {
                resourseManager.getStats().addToStat(position + 3, 1.0f);
            }
        }
    }

    @Override
    public void dispose() {

    }
}
