package io.github.some_example_name.Entities.Monster;

import java.util.HashMap;

public class BigZombie extends Zombie {

    public BigZombie() {
        super();
        speed = 1;
        size = 80;

        loadAnimations();
        type = "bigZombie";

        sprite.setSize(size, size);
        spawnOnEdge();
    }

    @Override
    public void update() {
        updateFrame();
        move();
    }

    private void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 6);
        idle.put("speed", 10);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 8);
        walk.put("speed", 10);

        animations.put("walk", walk);
        animations.put("idle", idle);
    }
}
