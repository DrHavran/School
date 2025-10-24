package io.github.some_example_name.Parts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.some_example_name.Settings;

public class Pipe {

    private Sprite sprite;
    private String texture;
    private boolean pointCheck;

    public Pipe() {
        if(Math.random() < 0.7){
            texture = "Green_pipe";
        }else{
            texture = "Red_pipe";
        }

        this.sprite = new Sprite();

        sprite.setSize(150, Settings.height*2);


        float x = Settings.width;
        float y = -200 + (int)(Math.random() * ((-Settings.height + 200) - (-200))); //-200 to -Settings.height+200
        sprite.setPosition(x, y);

        this.pointCheck = true;
    }

    public void update(float speed){
        sprite.setX(getX() - speed);
    }

    private float getX() {
        return sprite.getX();
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

    public void setTexture(Texture texture){
        sprite.setTexture(texture);
    }

    public String getTexture(){
        return texture;
    }
}
