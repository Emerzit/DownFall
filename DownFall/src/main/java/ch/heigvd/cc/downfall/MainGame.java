package ch.heigvd.cc.downfall;

import ch.heigvd.cc.downfall.view.GameFrame;

public class MainGame {

    public static void main(String[] args){
        GameFrame game = new GameFrame();
        game.init();
        game.run();
    }
}
