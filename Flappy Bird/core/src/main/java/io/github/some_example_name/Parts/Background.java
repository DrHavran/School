package io.github.some_example_name.Parts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.some_example_name.Settings;

public class Background extends Object {

    public Background() {
        Texture texture = new Texture("background.png");
        this.sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth()*5, Settings.height);
        sprite.setPosition(Settings.width, 0);
    }
    public Background(float x) {
        Texture texture = new Texture("background.png");
        this.sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth()*5, Settings.height);
        sprite.setPosition(x, 0);
    }

    @Override
    public void update(float speed){
        sprite.setX(getX()-speed);
    }

    private float getX(){
        return sprite.getX();
    }

}
