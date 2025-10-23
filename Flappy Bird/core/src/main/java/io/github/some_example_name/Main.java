package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private Logic logic;

    @Override
    public void create() {
        logic = Logic.getInstance();

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        logic.begin();
        logic.update();
        logic.end();
    }

    @Override
    public void dispose() {
        logic.dispose();
    }
}
