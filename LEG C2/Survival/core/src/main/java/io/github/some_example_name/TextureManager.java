package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

import java.io.File;
import java.util.HashMap;

public class TextureManager {
    private final HashMap<String, Texture> textures;

    public TextureManager() {
        this.textures = new HashMap<>();
        setUpTextures();
    }

    private void setUpTextures() {
        File folder = new File("assets/sprites");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                textures.put(file.getName(), new Texture(file.getAbsolutePath()));
            }
        }
    }

    public Texture getTexture(String name) {
        return textures.get(name + ".png");
    }
}
