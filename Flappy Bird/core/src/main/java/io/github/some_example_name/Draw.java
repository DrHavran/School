package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.some_example_name.Parts.Background;
import io.github.some_example_name.Parts.Pipe;

public class Draw {
    private final SpriteBatch batch; //draw stuff
    private final ShapeRenderer sr;
    private final BitmapFont font;

    private final Logic logic;

    public Draw() {
        this.logic = new Logic();

        this.batch = new SpriteBatch();
        this.sr = new ShapeRenderer();

        this.font = new BitmapFont(); //sets score font
        font.getData().setScale(1.2f);
        font.setColor(1, 0, 0, 1);
    }

    public void update(){

        logic.update();

        drawBackground();
        drawPipes();
        draw(logic.getBird());

        if(Settings.drawBounds){
            drawBounds();
        }

        font.draw(batch, "Score: " + logic.getScore(), 20, Settings.height-20);
    }

    private void drawBackground(){
        for(Background background : logic.getBackgrounds()){
            draw(background.getSprite());
        }
    }

    private void drawPipes(){
        for(Pipe pipe : logic.getPipes()){
            draw(pipe.getSprite());
        }
    }

    private void drawBounds(){
        batch.end();

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.polygon(logic.getBirdPoly().getTransformedVertices());

        for(Pipe pipe : logic.getPipes()){

            sr.rect(pipe.getSprite().getX(),
                pipe.getSprite().getY(),
                pipe.getSprite().getWidth(),
                pipe.getSprite().getHeight()/2 - 125); //top pipe

            sr.rect(
                pipe.getSprite().getX(),
                pipe.getSprite().getY() + pipe.getSprite().getHeight()/2 + 125,
                pipe.getSprite().getWidth(),
                pipe.getSprite().getHeight()/2 - 125); //bottom pipe

        }
        sr.end();

        batch.begin();
    }

    private void draw(Sprite sprite) {
        sprite.draw(batch);
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
