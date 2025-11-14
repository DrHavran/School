package io.github.some_example_name.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.HashMap;

public class Player extends Entity {

    public Player() {
        super();

        loadAnimations();
        changeAnimation("player_idle");

        size = 5;
        sprite.setSize(10 * size, 11 * size);
        sprite.setPosition(100, 100);
    }

    @Override
    public void update() {
        updateFrame();
        checkInputs();
    }

    private void checkInputs() {
        boolean pressed = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprite.setY(sprite.getY() + 5);
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprite.setY(sprite.getY() - 5);
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            flipped = true;
            pressed = true;
            sprite.setX(sprite.getX() - 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            flipped = false;
            pressed = true;
            sprite.setX(sprite.getX() + 5);
        }

        if (!pressed) {
            changeAnimation("player_idle");
        } else {
            changeAnimation("player_walk");
        }
    }

    private void changeAnimation(String string) {
        animation = string;
        frameCount = animations.get(string).get("frames");
        frameTimerMax = animations.get(string).get("speed");
    }

    private void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 2);
        idle.put("speed", 50);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 2);
        walk.put("speed", 20);

        animations.put("player_walk", walk);
        animations.put("player_idle", idle);
    }
}
