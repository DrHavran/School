package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private SpriteManager sm;

    @Override
    public void create() {
        sm = SpriteManager.getInstance();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        sm.begin();
        sm.update();
        sm.end();
    }

    @Override
    public void dispose() {
        sm.dispose();
    }
}
