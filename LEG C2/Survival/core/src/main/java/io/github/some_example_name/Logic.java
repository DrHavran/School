package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import io.github.some_example_name.Entities.Entity;
import io.github.some_example_name.Entities.Player;

import java.util.ArrayList;

public class Logic {

    private final ArrayList<Entity> entities;
    private final TextureManager tM;

    public Logic() {
        this.entities = new ArrayList<>();
        this.tM = new TextureManager();
        entities.add(new Player());
    }

    public void update(){
        for(Entity entity : entities){
            entity.update();
        }
    }

    public Texture getTexture(String name){return tM.getTexture(name);}
    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
