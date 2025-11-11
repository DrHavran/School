package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import io.github.some_example_name.Entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Logic {

    private final HashMap<String, Texture> textures;
    private ArrayList<Entity> entities;

    public Logic() {
        this.textures = new HashMap<>();
        setUpTextures();
    }

    public void update(){
        for(Entity entity : entities){
            entity.update();
        }
    }

    private void setUpTextures() {
        textures.put("player_idle", new Texture("./idle.png"));
        textures.put("player_walk", new Texture("./walk.png"));
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
