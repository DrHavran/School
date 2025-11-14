package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.some_example_name.Entities.Entity;

public class Draw {
    private final SpriteBatch batch; //draw stuff

    private final Logic logic;

    public Draw() {
        this.logic = new Logic();
        this.batch = new SpriteBatch();
    }

    public void update(){
        logic.update();

        for(Entity entity : logic.getEntities()){
            draw(entity);
        }
    }

    private void draw(Entity entity) {
        Sprite sprite = entity.getSprite();
        Texture texture = logic.getTexture(entity.getAnimation());
        int frameWidth = texture.getWidth() / entity.getFrameCount();
        batch.draw(
            texture,
            sprite.getX(), sprite.getY(),
            sprite.getWidth(), sprite.getHeight(),
            entity.getFrame()*frameWidth, 0,
            frameWidth, texture.getHeight(),
            entity.isFlipped(), false
        );
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
