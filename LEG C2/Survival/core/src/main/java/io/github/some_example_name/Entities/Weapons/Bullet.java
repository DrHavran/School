package io.github.some_example_name.Entities.Weapons;

import com.badlogic.gdx.Gdx;
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


        float radian = (float) Math.toRadians(angle + 180);

        double normX = Math.cos(radian);
        double normY = Math.sin(radian);

        this.x = (float) normX * speed;
        this.y = (float) normY * speed;

        sprite.setSize(10, 5);
        sprite.setRotation(angle);

        System.out.println(eM.weaponX());
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY();

        System.out.println(x + " " + y);
        System.out.println(angle);


        sprite.setPosition(
            (float) (eM.weaponX() + eM.weaponWidth() + normX * eM.weaponWidth()),
            eM.weaponY()
        );
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
