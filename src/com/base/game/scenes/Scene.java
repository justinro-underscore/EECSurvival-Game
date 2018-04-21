package com.base.game.scenes;

import java.io.IOException;

import bsh.EvalError;
import bsh.Interpreter;
import com.base.game.gameobject.entity.Player;

public class Scene {
    private Interpreter i;
    private String scriptFile;

    public Scene(String scriptFile, Player player) {
        i = new Interpreter();
        this.scriptFile = scriptFile;
        try {
            i.set("player", player);
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
