package io.github.some_example_name.Parts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.some_example_name.Settings;

public class Pipe {

    private final Sprite sprite;
    private boolean pointCheck;

    public Pipe(Texture texture) {

        this.sprite = new Sprite(texture);

        sprite.setSize(150, Settings.height*2);

        float x = Settings.width;
        float y = -200 + (int)(Math.random() * ((-Settings.height + 200) - (-200))); //-200 to -Settings.height+200
        sprite.setPosition(x, y);

        this.pointCheck = true;
    }

    public void update(float speed){
        sprite.setX(sprite.getX() - speed);
    }


    public boolean isPointCheck() {
        return pointCheck;
    }

    public void checkPoint() {
        this.pointCheck = false;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
