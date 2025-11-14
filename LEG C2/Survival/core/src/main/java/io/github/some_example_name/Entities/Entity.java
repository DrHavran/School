package io.github.some_example_name.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;

public class Entity {
    protected int frame;
    protected int frameCount;
    protected int frameTimer;
    protected int frameTimerMax;

    protected HashMap<String, HashMap<String, Integer>> animations;

    protected Sprite sprite;
    protected String animation;
    protected int size;
    protected boolean flipped;

    public Entity(){
        this.animations = new HashMap<>();
        this.sprite = new Sprite();

        this.flipped = false;

        this.frame = 0;
        this.frameTimer = 0;
    }

    public void update(){}

    protected void updateFrame(){
        frameTimer++;
        if(frameTimer >= frameTimerMax){
            frameTimer = 0;
            frame++;
            if(frame >= frameCount){
                frame = 0;
            }
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
    public int getFrame() {
        return frame;
    }
    public String getAnimation() {
        return animation;
    }
    public boolean isFlipped() {
        return flipped;
    }
    public int getFrameCount(){
        return frameCount;
    }
}
