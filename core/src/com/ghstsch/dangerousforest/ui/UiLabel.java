package com.ghstsch.dangerousforest.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Aaa on 24.06.2015.
 */
public class UiLabel extends UiElement{
    private CharSequence text;
    private float size;
    public UiLabel(CharSequence text, float size, float x, float y, Color color, BitmapFont font) {
        super(x, y, color, font);
        this.text = text;
        this.size = size;
    }
    public  void drawElement(SpriteBatch batch) {
        font.setColor(color);
        font.setScale(size);
        font.draw(batch, text, x, y);
    }
    public void setText(CharSequence text) {
        this.text = text;
    }
}
