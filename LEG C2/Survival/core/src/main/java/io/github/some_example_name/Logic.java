package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import io.github.some_example_name.Entities.Entity;
import io.github.some_example_name.Entities.Monster.SmallZombie;
import io.github.some_example_name.Entities.Player.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Logic {

    private final ArrayList<Entity> entities;
    private final HashMap<String, Texture> textures;

    private final Player player;

    public Logic() {
        this.entities = new ArrayList<>();
        this.textures = new HashMap<>();
        setUpTextures();

        this.player = new Player();

        entities.add(player);
        //entities.add(new SmallZombie(player));
    }

    public void update(){
        for(Entity entity : entities){
            entity.update();
        }
    }

    private void setUpTextures() {
        File folder = new File("assets/sprites");
        File[] subfolders = folder.listFiles();
        assert subfolders != null;
        for (File sub : subfolders) {
            File[] files = sub.listFiles();
            assert files != null;
            for (File img : files) {
                System.out.println("Loading " + img.getName());
                textures.put(img.getName(), new Texture(img.getAbsolutePath()));
            }
        }
    }

    public Texture getTexture(String name){return textures.get(name + ".png");}
    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
