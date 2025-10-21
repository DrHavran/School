package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class SpriteManager {
    public static SpriteManager instance;
    public static SpriteManager getInstance() {
        if (instance == null) {
            instance = new SpriteManager();
        }
        return instance;
    }

    private final SpriteBatch batch;
    private final ArrayList<Object> objects;
    private int pipeCount;

    public SpriteManager() {
        batch = new SpriteBatch();
        objects = new ArrayList<>();
        pipeCount = 0;

        objects.add(new Bird());
    }

    private void draw(Texture texture, float x, float y) {
        batch.draw(texture, x, y);
    }

    public void update(){
        pipeCount++;
        if (pipeCount == Settings.pipeSpeed) {
            pipeCount = 0;
            objects.add(new Pipe());
        }
        for(Object object : objects){
            object.update();
            draw(object.getTexture(), object.getX(), object.getY());
        }
    }

    public void begin() {
        batch.begin();
    }

    public void end() {
        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }
}
