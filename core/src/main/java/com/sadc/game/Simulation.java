package com.sadc.game;

import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.utils.Disposable;

public class Simulation implements Disposable{

    private void populate(){
        ObjLoader objLoader = new ObjLoader();
        /*
         example found here: https://github.com/libgdx/libgdx/blob/master/demos/invaders/gdx-invaders/src/com/badlogic/gdxinvaders/simulation/Simulation.java
         */
    }

    public void update(){
        //run through all update methods
    }
    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        //whatever.displose();
    }
}
