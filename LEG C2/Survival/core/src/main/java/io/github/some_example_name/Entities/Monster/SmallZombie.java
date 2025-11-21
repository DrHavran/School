package io.github.some_example_name.Entities.Monster;

import java.util.HashMap;

public class SmallZombie extends Zombie {

    public SmallZombie() {
        super();
        speed = 5;
        size = 60;
        damage = 20;

        loadAnimations();
        type = "smallZombie";

        sprite.setSize(size, size);
        spawnOnEdge();
    }

    private void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 6);
        idle.put("speed", 6);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 6);
        walk.put("speed", 7);

        animations.put("walk", walk);
        animations.put("idle", idle);
    }
}
