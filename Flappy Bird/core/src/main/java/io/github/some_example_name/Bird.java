package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

public class Bird extends Object {

    public Bird() {
        this.texture = new Texture("Bird.png");
        this.x = 200;
        this.y = 200;
    }

    @Override
    public void update(){
        y -= 1;
    }
}
