package com.greatgame.application.mainMenuMode;

import com.greatgame.application.GreatGame;
import com.greatgame.application.Mode;
import com.greatgame.environment.Environment;
import com.greatgame.world.World;

public class MainMenuMode extends Mode {
    Mode previousMode;

    public MainMenuMode(GreatGame app, Mode previousMode, boolean pause) {
        this.previousMode = previousMode;
        this.app = app;
        if (pause) {
            stage = new PausePage(this).pageStage;
        } else {
            stage = new MainMenuPage(this).pageStage;
        }
    }

    public Mode getPreviousMode() {
        return previousMode;
    }

    public boolean hasPreviousMode() {
        return previousMode != null;
    }

    void newGameLoaded() {
        previousMode = null;
    }

    GreatGame getApp() {
        return app;
    }

    Environment environment() {
        return getEnvironment();
    }

    World world() {
        return getWorld();
    }

    void changePage(Page newPage) {
        stage = newPage.pageStage;
        app.updateInputMultiplexer();
    }
}