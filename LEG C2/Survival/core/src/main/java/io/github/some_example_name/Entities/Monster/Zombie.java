package io.github.some_example_name.Entities.Monster;

import io.github.some_example_name.Entities.Entity;
import io.github.some_example_name.Entities.Player.Player;
import io.github.some_example_name.Settings;

public class Zombie extends Entity {
    private final Player player;

    public Zombie(Player player) {
        super();
        this.player = player;
    }

    protected void move(){
        moveToPlayer();
    }

    private void moveToPlayer() {
        float x = player.getSprite().getX() - sprite.getX();
        float y = player.getSprite().getY() - sprite.getY();

        float length = (float) Math.sqrt(x * x + y * y);

        float normalizeX = (x / length) * speed;
        float normalizeY = (y / length) * speed;

        sprite.setPosition(sprite.getX() + normalizeX, sprite.getY() + normalizeY);

        if (normalizeX < 0) {
            changeAnimation(type + "_walk_left");
        } else if (normalizeX > 0) {
            changeAnimation(type + "_walk_right");
        } else if (normalizeY < 0) {
            changeAnimation(type + "_walk_up");
        } else if (normalizeY > 0) {
            changeAnimation(type + "_walk_down");
        }

        if (Math.abs(normalizeX) > Math.abs(normalizeY)) {
            if (normalizeX < 0) {
                changeAnimation(type + "_walk_left");
            } else {
                changeAnimation(type + "_walk_right");
            }
        }else {
            if (normalizeY < 0) {
                changeAnimation(type + "_walk_down");
            }else {
                changeAnimation(type + "_walk_up");
            }
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
