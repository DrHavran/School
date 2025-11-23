package io.github.some_example_name.Entities.Weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.some_example_name.Entities.Entity;
import io.github.some_example_name.Settings;

public class Weapon extends Entity {

    protected int ammo;

    protected int count;
    protected int shootDelay;

    private boolean shooting;

    public Weapon() {
        super();
        this.shooting = false;
        count = shootDelay;
        changeAnimation("idle");
    }

    @Override
    public void update(){
        float[] vector = countVector();
        float angle = (float) Math.toDegrees(Math.atan2(-vector[1], -vector[0]));

        sprite.setRotation(angle);

        if(vector[0] > 0){
            rotation = "right";
        }else{
            rotation = "left";
        }

        if(count < shootDelay){
            count++;
        }

        if(shooting){
            if(count == animations.get("shoot").get("frames")){
                shooting = false;
            }
        }else {
            changeAnimation("idle");
        }

        sprite.setPosition(
            eM.playerX() + eM.playerWidth()/2 - sprite.getOriginX(),
            eM.playerY() + eM.playerHeight()/2 - sprite.getOriginY()*2
        );

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if((ammo > 0 || Settings.unlimitedAmmo) && count == shootDelay){
                ammo--;
                shooting = true;

                count = 0;
                frame = 0;
                frameTimerMax = 0;

                changeAnimation("shoot");
                eM.addEntity(new Bullet(angle));
            }
        }

    }
}
