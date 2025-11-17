package io.github.some_example_name.Entities.Weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.some_example_name.Entities.Entity;

public class Weapon extends Entity {

    protected int ammo;
    protected int delay;

    protected int shootDelay;
    private boolean shooting;

    public Weapon() {
        super();
        this.shooting = false;
        changeAnimation("idle");
    }

    protected void upperUpdate(){
        float[] vector = countVector();
        float angle = (float) Math.toDegrees(Math.atan2(-vector[1], -vector[0]));

        sprite.setRotation(angle);

        if(vector[0] > 0){
            rotation = "right";
        }else{
            rotation = "left";
        }

        if(shooting){
            delay--;
            if(delay >= shootDelay-3){
                
            }
        }

        sprite.setPosition(
            eM.playerX() + eM.playerWidth()/2 - sprite.getOriginX(),
            eM.playerY() + eM.playerHeight()/2 - sprite.getOriginY()*2
        );

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if(ammo > 0){
                ammo--;
                shooting = true;
                delay = shootDelay;
                eM.addEntity(new Bullet(angle));
            }
        }

    }
}
