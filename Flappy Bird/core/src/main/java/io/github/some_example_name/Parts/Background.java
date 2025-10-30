package io.github.some_example_name.Parts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.some_example_name.Settings;

public class Background {

    private final Sprite sprite;

    public Background(Texture texture, float x) {
        this.sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth()*5, Settings.height);
        sprite.setPosition(x, 0);
    }

    public void update(float speed){
        sprite.setX(sprite.getX() - speed);
    }

    public Sprite getSprite() {
        return sprite;
    }

}
