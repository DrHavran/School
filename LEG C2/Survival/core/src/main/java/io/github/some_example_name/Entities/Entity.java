package io.github.some_example_name.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Entity {
    void update();
    Sprite getSprite();
    float getX();
    float getY();
    int getFrame();
    int getNumberOfSprites();
}
