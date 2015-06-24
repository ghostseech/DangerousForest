package com.ghstsch.dangerousforest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DangerousForest extends ApplicationAdapter {
				ScreenManager screenManager;
				ResourseManager resourseManager;
				@Override
				public void create () {
								//font = new BitmapFont(Gdx.files.internal("fonts/font_8.fnt"), Gdx.files.internal("fonts/font_8_0.png"), true);
								screenManager = new ScreenManager();

								resourseManager = new ResourseManager();
								resourseManager.init();
								screenManager.setResourse(resourseManager);

								screenManager.setScreen(ScreenManager.MENU_SCREEN, true);

				}

				@Override
				public void render () {
								InputHandler.update();
								screenManager.update(Gdx.graphics.getDeltaTime());
								screenManager.draw();
				}
}
