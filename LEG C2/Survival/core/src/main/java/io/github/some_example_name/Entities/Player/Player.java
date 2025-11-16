package io.github.some_example_name.Entities.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.some_example_name.Entities.Entity;

import java.util.HashMap;

public class Player extends Entity {

    private boolean punch;

    public Player() {
        super();
        size = 60;
        speed = 5;

        loadAnimations();
        type = "player";
        punch = false;
        changeAnimation(type + "_idle_down");

        sprite.setSize(size, size);
        sprite.setPosition(100, 100);
    }

    @Override
    public void update() {
        this.updateFrame();
        checkInputs();
    }

    @Override
    protected void updateFrame(){
        frameTimer++;
        if(frameTimer >= frameTimerMax){
            frameTimer = 0;
            frame++;
            if(frame >= frameCount){
                frame = 0;
                if(punch){
                    punch = false;
                }
            }
        }
    }

    private void checkInputs() {
        boolean pressed = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprite.setY(sprite.getY() + speed);
            rotation = "up";
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprite.setY(sprite.getY() - speed);
            rotation = "down";
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            pressed = true;
            rotation = "left";
            sprite.setX(sprite.getX() - speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            pressed = true;
            rotation = "right";
            sprite.setX(sprite.getX() + speed);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)&&!punch){
            punch = true;
            frame = 0;
            frameTimer = 0;
            changeAnimation(type + "_punch_" + rotation);
            if(rotation.equals("right")||rotation.equals("left")){
                sprite.setSize((float) (size*1.6d), size);
            }else {
                sprite.setSize(size, size);
            }
            return;
        }

        if (!pressed&&!punch) {
            changeAnimation(type + "_idle_" + rotation);
            sprite.setSize(size, size);
        } else if(pressed&&!punch){
            changeAnimation(type + "_walk_" + rotation);
            sprite.setSize(size, size);
        }
    }

    private void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 6);
        idle.put("speed", 7);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 6);
        walk.put("speed", 7);

        HashMap<String, Integer> punch = new HashMap<>();
        punch.put("frames", 4);
        punch.put("speed", 7);

        animations.put("walk", walk);
        animations.put("punch", punch);
        animations.put("idle", idle);
    }
}
