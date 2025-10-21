package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

public class Object {
    protected float x;
    protected float y;
    protected Texture texture;

    public void update(){};

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getTexture() {
        return texture;
    }
}
