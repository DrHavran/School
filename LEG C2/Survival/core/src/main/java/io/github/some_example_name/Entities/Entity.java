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
    protected String type;
    protected int size;
    protected String rotation;

    protected int speed;

    public Entity(){
        this.animations = new HashMap<>();
        this.sprite = new Sprite();

        this.speed = 0;
        this.frame = 0;
        this.frameTimer = 0;
        this.rotation = "down";
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

    protected void changeAnimation(String string) {
        animation = string;
        //System.out.println("current animation " + animation);
        String type = string.split("_")[1];
        frameCount = animations.get(type).get("frames");
        frameTimerMax = animations.get(type).get("speed");
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
    public int getFrameCount(){
        return frameCount;
    }
    public String getRotation() { return rotation; }
}
