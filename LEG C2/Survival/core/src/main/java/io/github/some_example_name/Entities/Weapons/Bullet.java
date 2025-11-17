package io.github.some_example_name.Entities.Weapons;

import io.github.some_example_name.Entities.Entity;
import io.github.some_example_name.Entities.Monster.Zombie;

public class Bullet extends Entity {

    private final float x;
    private final float y;

    public Bullet(float angle) {
        super();
        rotation = "null";
        type = "bullet";
        changeAnimation("idle");
        speed = 5;

        sprite.setSize(10, 5);
        sprite.setRotation(angle);
        sprite.setPosition(eM.weaponX() + eM.weaponWidth(), eM.weaponY() + eM.weaponHeight() / 2);

        float radian = (float) Math.toRadians(angle + 180);
        this.x = (float) Math.cos(radian) * speed;
        this.y = (float) Math.sin(radian) * speed;
    }

    @Override
    public void update() {
        sprite.setPosition(sprite.getX() + x, sprite.getY() + y);

        for(Entity entity : eM.getEntities()){
            if(entity instanceof Zombie){
                if(sprite.getBoundingRectangle().overlaps(entity.getSprite().getBoundingRectangle())){
                    eM.removeEntity(entity);
                }
            }
        }
    }
}
