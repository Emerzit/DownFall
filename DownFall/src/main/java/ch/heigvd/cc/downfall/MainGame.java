package ch.heigvd.cc;

import ch.heigvd.cc.view.GameFrame;

public class MainGame {

    public static void main(String[] args){
        GameFrame game = new GameFrame();
        game.init();
        game.run();
    }
}
