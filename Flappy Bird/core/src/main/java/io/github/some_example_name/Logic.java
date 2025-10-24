package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import io.github.some_example_name.Parts.Background;
import io.github.some_example_name.Parts.Bird;
import io.github.some_example_name.Parts.Object;
import io.github.some_example_name.Parts.Pipe;

import java.util.ArrayList;

public class Logic {

    public static Logic instance; //singleton
    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    private final SpriteBatch batch; //draw stuff
    private final ShapeRenderer sr;
    private final BitmapFont font;

    private final Sound sound;

    private final ArrayList<io.github.some_example_name.Parts.Object> objects; //logic stuff
    private final ArrayList<io.github.some_example_name.Parts.Object> remove;
    private float pipeCount; //Counts frames between pipes spawn
    private float pipeAmount; //Line for pipeCount to cross
    private float pipeSpeed; //Speed of pipes
    private final Bird bird;
    private int score;

    public Logic() {
        this.batch = new SpriteBatch();
        this.sr = new ShapeRenderer();
        this.font = new BitmapFont();
        font.getData().setScale(1.2f);
        font.setColor(1, 0, 0, 1);

        this.sound = Gdx.audio.newSound(Gdx.files.internal("point.mp3"));

        this.objects = new ArrayList<>();
        this.remove = new ArrayList<>();
        this.pipeAmount = 200f;
        this.pipeCount = pipeAmount -1f;
        this.pipeSpeed = 4;
        this.bird = new Bird();
        setUpBackground();
    }

    private void setUpBackground(){
        float filled = 0f;
        while(filled < Settings.width){
            Background background = new Background(filled);
            filled += background.getSprite().getWidth();
            objects.add(background);
        }
    }

    public void update(){
        pipeCount++;
        if (pipeCount >= pipeAmount) {
            pipeCount = 0f;
            objects.add(new Pipe());
        }

        draObjects();
        drawBird();
        font.draw(batch, "Score: " + score, 20, Settings.height-20);
    }

    private void drawBird(){
        bird.update();
        if(bird.getSprite().getY() < 0){ //closes the app when player touches the ground
            System.out.println("Final Score: " + score);
            Gdx.app.exit();
        }
        draw(bird.getSprite());
    }

    private void draObjects(){
        for(io.github.some_example_name.Parts.Object object : objects){
            if(object instanceof Pipe){

                object.update(pipeSpeed);
                draw(object.getSprite());

                checkTouch(object.getSprite());

                if(object.getSprite().getX()<0-object.getSprite().getWidth()){
                    remove.add(object);
                }

                if(((Pipe) object).isPointCheck()&&object.getSprite().getX()<bird.getSprite().getWidth()){
                    score++;
                    pipeSpeed += 0.2f;
                    pipeAmount -= pipeSpeed * 2/3;
                    sound.play(1f);
                    ((Pipe) object).checkPoint();
                }
            }else{
                object.update(pipeSpeed);
                draw(object.getSprite());

                if(object.getSprite().getX()<0-object.getSprite().getWidth()){
                    remove.add(object);
                }
            }
        }

        for(Object object : remove){
            objects.remove(object);
        }
        remove.clear();
    }

    private void checkTouch(Sprite pipe){

        Polygon birdBound = new Polygon(new float[]{
            0, 0,
            bird.getSprite().getWidth(), 0,
            bird.getSprite().getWidth(), bird.getSprite().getHeight(),
            0, bird.getSprite().getHeight()
        });

        birdBound.setPosition(bird.getSprite().getX(), bird.getSprite().getY());
        birdBound.setOrigin(bird.getSprite().getWidth()/2, bird.getSprite().getHeight()/2);
        birdBound.setRotation(bird.getSprite().getRotation());

        Polygon botPipe = new Polygon(new float[]{
            0, 0,
            pipe.getWidth(), 0,
            pipe.getWidth(), pipe.getHeight()/2 - 125,
            0, pipe.getHeight()/2 - 125
        });

        botPipe.setPosition(pipe.getX(), pipe.getY());

        Polygon topPipe = new Polygon(new float[]{
            0, 0,
            pipe.getWidth(), 0,
            pipe.getWidth(), pipe.getHeight()/2 - 125,
            0, pipe.getHeight()/2 - 125
        });

        topPipe.setPosition(pipe.getX(), pipe.getY() + pipe.getHeight()/2 + 125);

        batch.end();
        if(Settings.drawBounds){
            sr.begin(ShapeRenderer.ShapeType.Line);

            sr.polygon(topPipe.getTransformedVertices());
            sr.polygon(botPipe.getTransformedVertices());
            sr.polygon(birdBound.getTransformedVertices());

            sr.end();
        }
        batch.begin();

        if(Intersector.overlapConvexPolygons(birdBound, topPipe)||Intersector.overlapConvexPolygons(birdBound, botPipe)){
            System.out.println("Final Score: " + score);
            Gdx.app.exit();
        }

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
        sound.dispose();
    }
}
