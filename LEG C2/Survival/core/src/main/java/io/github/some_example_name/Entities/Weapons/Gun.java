package io.github.some_example_name.Entities.Weapons;

import java.util.HashMap;

public class Gun extends Weapon{

    public Gun() {
        super();
        size = 50;

        loadAnimations();
        type = "gun";
        rotation = "left";
        changeAnimation("idle");

        ammo = 20;
        shootDelay = 10;

        sprite.setSize((float) (size*1.2), (float) size /2);
        sprite.setOrigin(sprite.getWidth(), sprite.getHeight()/2);
    }

    private void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 6);
        idle.put("speed", 7);

        HashMap<String, Integer> shoot = new HashMap<>();
        shoot.put("frames", 3);
        shoot.put("speed", 7);

        HashMap<String, Integer> reload = new HashMap<>();
        reload.put("frames", 8);
        reload.put("speed", 7);

        animations.put("idle", idle);
        animations.put("shoot", shoot);
        animations.put("reload", reload);
    }
}
