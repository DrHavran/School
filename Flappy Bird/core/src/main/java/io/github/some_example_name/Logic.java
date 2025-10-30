package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import io.github.some_example_name.Parts.Background;
import io.github.some_example_name.Parts.Bird;
import io.github.some_example_name.Parts.Pipe;

import java.util.ArrayList;
import java.util.HashMap;

public class Logic {
    private final ArrayList<Pipe> pipes; //logic stuff
    private final ArrayList<Pipe> remove;
    private final ArrayList<Background> backgrounds;
    private float pipeCount; //Counts frames between pipes spawn
    private float pipeAmount; //Line for pipeCount to cross
    private float pipeSpeed; //Speed of pipes

    private final HashMap<String, Texture> textures;

    private final Bird bird;
    private int score;
    private final Sound sound;

    public Logic() {
        this.pipes = new ArrayList<>();
        this.remove = new ArrayList<>();
        this.backgrounds = new ArrayList<>();
        this.sound = Gdx.audio.newSound(Gdx.files.internal("point.mp3"));

        this.pipeAmount = 200f;
        this.pipeCount = pipeAmount - 1f;
        this.pipeSpeed = 4;

        this.textures = new HashMap<>();
        setUpTextures();

        this.bird = new Bird(textures.get("Bird"));
        setUpBackground();
    }

    private void setUpTextures() {
        textures.put("Green_Pipe", new Texture("Green_pipe.png"));
        textures.put("Red_Pipe", new Texture("Red_pipe.png"));
        textures.put("Bird", new Texture("Bird.png"));
        textures.put("Background", new Texture("Background.png"));
    }

    private void setUpBackground(){
        float filled = 0f;
        while(filled < Settings.width){
            Background background = new Background(textures.get("Background"), filled);
            filled += background.getSprite().getWidth();
            backgrounds.add(background);
        }
        Background background = new Background(textures.get("Background"), filled);
        backgrounds.add(background);
    }

    public void update(){
        bird.update();
        updatePipes();
        updateBackground();


        if(bird.getY() < 0){ //closes the app when player touches the ground
            System.out.println("Final Score: " + score);
            Gdx.app.exit();
        }

    }

    private void updateBackground() {
        for(Background background : backgrounds){
            background.update(pipeSpeed);

            if(background.getSprite().getX() < 0 - background.getSprite().getWidth()){
                background.getSprite().setX(background.getSprite().getX() + backgrounds.size() * background.getSprite().getWidth());
            }
        }
    }

    private void updatePipes(){
        for(Pipe pipe : pipes){
            pipe.update(pipeSpeed);

            checkTouch(pipe.getSprite());

            if(pipe.getSprite().getX()<0-pipe.getSprite().getWidth()){ //if pipe passes off-screen
                remove.add(pipe);
            }

            if(pipe.isPointCheck() && pipe.getSprite().getX() < bird.getSprite().getWidth()){ //checks if the player has gotten through a pipe
                score++;
                pipeSpeed += 0.2f;
                pipeAmount -= pipeSpeed * 2/3;
                sound.play(1f);
                pipe.checkPoint();
            }
        }

        for(Pipe pipe : remove){
            pipes.remove(pipe);
        }
        remove.clear();

        pipeCount++; //checks when to spawn a new pipe
        if (pipeCount >= pipeAmount) {
            pipeCount = 0f;

            if(Math.random() < 0.6){
                pipes.add(new Pipe(textures.get("Green_Pipe")));
            }else{
                pipes.add(new Pipe(textures.get("Red_Pipe")));
            }
        }
    }

    private void checkTouch(Sprite pipe){

        Polygon birdBound = getBirdPoly();

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

        if(Intersector.overlapConvexPolygons(birdBound, topPipe)||Intersector.overlapConvexPolygons(birdBound, botPipe)){
            System.out.println("Final Score: " + score);
            Gdx.app.exit();
        }
    }

    public int getScore() {
        return score;
    } //getters

    public Sprite getBird() {
        return bird.getSprite();
    }

    public Polygon getBirdPoly(){
        return bird.getPolygon();
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public ArrayList<Background> getBackgrounds() {
        return backgrounds;
    }
}
