package io.github.some_example_name.Entities.Monster;

import io.github.some_example_name.Entities.Player.Player;

import java.util.HashMap;

public class SmallZombie extends Zombie {

    public SmallZombie(Player player) {
        super(player);
        speed = 5;
        size = 60;

        loadAnimations();
        type = "smallZombie";

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
        idle.put("speed", 7);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 6);
        walk.put("speed", 7);

        animations.put("walk", walk);
        animations.put("idle", idle);
    }
}
