package io.github.some_example_name.Parts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import io.github.some_example_name.Settings;

public class Bird {

    private final Sprite sprite;
    private float speed;
    private Polygon polygon;

    public Bird(Texture texture) {
        this.sprite = new Sprite(texture);

        sprite.setSize(70f, 50f); //sprite setup
        sprite.setOriginCenter();
        sprite.setPosition(200f, 200f);
        sprite.setRotation(0f);


        polygon = new Polygon(new float[]{ //sets up polygon for checks / rendering bounds
            0, 0,
            sprite.getWidth(), 0,
            sprite.getWidth(), sprite.getHeight(),
            0, sprite.getHeight()
        });
        polygon.setOrigin(sprite.getWidth() / 2f, sprite.getHeight() / 2f);

        this.speed = 0f;
    }

    public void update(){

        if(getY() + sprite.getHeight() > Settings.height) { //checks for top collisions
            setY(Settings.height - sprite.getHeight());
            speed = 0f;
        }

        if(sprite.getRotation()>-40f){ //rotates the bird
            setRotation(getRotation() - 0.6f);
        }

        speed += Gdx.graphics.getDeltaTime() * Settings.speed; //falling speed
        setY(getY() - speed);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { //check for jump
            speed = -5f;
            setRotation( 40f );
        }

        polygon.setPosition(sprite.getX(), sprite.getY());
        polygon.setRotation(sprite.getRotation());
    }

    private float getRotation() {
        return sprite.getRotation();
    } //makes code cleaner

    private void setRotation(float rotation) {
        sprite.setRotation(rotation);
    }

    private void setY(float y){
        sprite.setY(y);
    }


    public float getY() {
        return sprite.getY();
    } //actual getters

    public Sprite getSprite() {
        return sprite;
    }

    public Polygon getPolygon(){
        return polygon;
    }
}
