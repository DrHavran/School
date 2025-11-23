package io.github.some_example_name.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import io.github.some_example_name.Managers.EntityManager;

import java.util.HashMap;

public class Entity {
    protected int frame;
    protected int frameCount;
    protected int frameTimer;
    protected int frameTimerMax;

    protected HashMap<String, HashMap<String, Integer>> animations;
    protected final EntityManager eM;

    protected Sprite sprite;
    protected String animation;
    protected String type;
    protected String rotation;
    protected int size;

    protected int speed;
    protected int health;
    protected int damage;

    public Entity(){
        this.animations = new HashMap<>();
        this.sprite = new Sprite();
        this.eM = EntityManager.getInstance();

        this.speed = 0;
        this.frame = 0;
        this.frameTimer = 0;
        this.frameCount = 1;
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
        animation = type + "_" + string + "_";
        //System.out.println("current animation " + animation);
        if(!animations.isEmpty()){
            frameCount = animations.get(string).get("frames");
            frameTimerMax = animations.get(string).get("speed");
        }
    }

    protected float[] countVector(){
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY();

        float playerX = sprite.getX() + sprite.getWidth() / 2;
        float playerY = sprite.getY() + sprite.getHeight() / 2;

        return new float[] { x - playerX, y - playerY };
    }

    public Sprite getSprite() {
        return sprite;
    }
    public int getFrame() {
        return frame;
    }
    public Rectangle bounds(){
        return sprite.getBoundingRectangle();
    }
    public String getAnimation() {
        return animation;
    }
    public int getFrameCount(){
        return frameCount;
    }
    public String getRotation(){
        return rotation;
    }
}
