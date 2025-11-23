package io.github.some_example_name.Entities.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.some_example_name.Entities.Entity;

import java.util.HashMap;

public class Player extends Entity {

    public Player() {
        super();
        size = 60;
        speed = 5;
        health = 100;

        loadAnimations();
        type = "player";
        changeAnimation("idle");

        sprite.setSize((float) (size*0.8), size);
        sprite.setPosition(100, 100);
    }

    @Override
    public void update() {
        if(health <= 0){
            Gdx.app.exit();
        }

        updateFrame();
        checkInputs();
    }

    private void checkInputs() {
        rotate();
        move();
    }

    private void rotate(){
        float[] vector = countVector();

        if (Math.abs(vector[0]) > Math.abs(vector[1])) {
            if(vector[0] > 0){
                rotation = "right";
            }else{
                rotation = "left";
            }
        } else {
            if(vector[1] > 0){
                rotation = "up";
            }else{
                rotation = "down";
            }
        }
    }

    private void move(){
        boolean pressed = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprite.setY(sprite.getY() + speed);
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprite.setY(sprite.getY() - speed);
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            pressed = true;
            sprite.setX(sprite.getX() - speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            pressed = true;
            sprite.setX(sprite.getX() + speed);
        }

        if (!pressed) {
            changeAnimation("idle");
        } else{
            changeAnimation("walk");
        }
    }

    private void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 6);
        idle.put("speed", 7);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 6);
        walk.put("speed", 7);

        animations.put("walk", walk);
        animations.put("idle", idle);
    }

    public void damage(int hit) {
        health -= hit;
    }
}
