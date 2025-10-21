package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

public class Pipe extends Object{

    public Pipe() {
        this.texture = new Texture("Pipe.png");
        this.x = 400;
        this.y = 0;
    }

    @Override
    public void update(){
        x -= 1;
    }
}
