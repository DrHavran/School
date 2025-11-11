package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        TextureRegion region = new TextureRegion(entity.getSprite(), entity.getFrame()*entity.getNumberOfSprites(), 0, 11, (int) entity.getSprite().getHeight());
        batch.draw(region, entity.getX(), entity.getY());
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
