package io.github.some_example_name.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    private int frame;
    private Sprite sprite;
    private final int numberOfSprites;

    public Player() {
        this.sprite = new Sprite();
        this.frame = 0;
        numberOfSprites = (int) (sprite.getWidth()/11);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getX(){
        return sprite.getX();
    }
    public float getY(){
        return sprite.getY();
    }
    public int getFrame(){return frame;}
    public int getNumberOfSprites(){return numberOfSprites;}
}
