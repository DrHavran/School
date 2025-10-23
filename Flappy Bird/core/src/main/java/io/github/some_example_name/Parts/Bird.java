package io.github.some_example_name.Parts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.some_example_name.Settings;

public class Bird {

    private final Sprite sprite;
    private float speed;

    public Bird() {
        Texture texture = new Texture("Bird.png");

        this.sprite = new Sprite(texture);
        sprite.setSize(70f, 50f);
        sprite.setOriginCenter();
        sprite.setPosition(200f, 200f);
        sprite.setRotation(0f);

        this.speed = 0f;
    }

    public void update(){

        if(sprite.getY() + sprite.getHeight() > Settings.height) { //checks for top collisions
            sprite.setY(Settings.height - sprite.getHeight());
            speed = 0f;
        }

        if(sprite.getRotation()>-40f){ //rotates the bird
            sprite.setRotation(getRotation()-0.6f);
        }

        speed += Gdx.graphics.getDeltaTime() * Settings.speed; //falling speed
        sprite.setY(getY()-speed);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { //check for jump
            speed = -5f;
            sprite.setRotation(40f);
        }
    }

    private float getRotation() {
        return sprite.getRotation();
    }

    private float getY() {
        return sprite.getY();
    }

    public Sprite getSprite() {
        return sprite;
    }
}
