package com.base.game.scenes;

import java.io.IOException;

import bsh.EvalError;
import bsh.Interpreter;
import com.base.game.gameobject.entity.Boss;
import com.base.game.gameobject.entity.Player;
import com.base.game.levels.Level;

public class Scene {
    private Interpreter i;
    private String scriptFile;

    public Scene(String scriptFile, Player player, Boss boss, Level level) {
        i = new Interpreter();
        this.scriptFile = scriptFile;
        try {
            i.set("player", player);
            i.set("boss", boss);
            i.set("level", level);
        } catch (EvalError evalError) {
            evalError.printStackTrace();
        }
    }

    public void run() {
        try {
            i.source(scriptFile);
        } catch (IOException | EvalError e) {
            e.printStackTrace();
        }
    }
}
