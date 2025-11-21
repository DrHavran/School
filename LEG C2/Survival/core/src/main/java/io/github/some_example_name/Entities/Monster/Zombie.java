package io.github.some_example_name.Entities.Monster;

import io.github.some_example_name.Entities.Entity;
import io.github.some_example_name.Settings;

public class Zombie extends Entity {

    protected int damageDelay;
    protected int damageCount;

    public Zombie() {
        super();
        damageDelay = 200;
        damageCount = 0;
    }

    @Override
    public void update(){
        if(damageCount < damageDelay){
            damageCount++;
        }
        updateFrame();
        moveToPlayer();
        checkPlayer();
    }

    private void moveToPlayer() {
        float x = eM.playerX() - sprite.getX();
        float y = eM.playerY() - sprite.getY();

        float length = (float) Math.sqrt(x * x + y * y);

        float normalizeX = (x / length);
        float normalizeY = (y / length);

        sprite.setPosition(sprite.getX() + normalizeX * speed, sprite.getY() + normalizeY * speed);

        if (Math.abs(normalizeX) > Math.abs(normalizeY)) {
            if (normalizeX < 0) {
                rotation = "left";
                changeAnimation("walk");
            } else {
                rotation = "right";
                changeAnimation("walk");
            }
        }else {
            if (normalizeY < 0) {
                rotation = "down";
                changeAnimation("walk");
            }else {
                rotation = "up";
                changeAnimation("walk");
            }
        }
    }

    private void checkPlayer(){
        if(!Settings.safeMode && bounds().overlaps(eM.getPlayer().bounds()) && damageCount == damageDelay){
            eM.getPlayer().damage(damage);
            damageCount = 0;
        }
    }

    protected void spawnOnEdge(){
        int side = (int)(Math.random() * 4); //0 - up, 1 - right, 2 - down, 3 - left
        double x;
        double y;
        switch (side){
            case 0:
                x = Settings.height + sprite.getHeight();
                y = Math.random() * Settings.width;
                break;
            case 1:
                x = Settings.width + sprite.getWidth();
                y = Math.random() * Settings.height;
                break;
            case 2:
                y = 0 - sprite.getHeight();
                x = Math.random() * Settings.width;
                break;
            case 3:
                x = 0 - sprite.getWidth();
                y = Math.random() * Settings.height;
                break;
            default:
                x = 0;
                y = 0;
                break;

        }
        sprite.setPosition((float)x, (float)y);
    }
}
