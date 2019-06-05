package ch.heigvd.cc.downfall.view;

import ch.heigvd.cc.downfall.modele.PlayerModel;
import ch.heigvd.cc.downfall.modele.PlayerPannelModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {

    GameFrame game;

    @BeforeEach
    void init() {
        game = new GameFrame();
        game.init();
        //commencer le jeu
        game.curState=2;
        game.initGame();
    }

    @Test
    void GameInit() {
        //test qu'il y ait 2 joueurs
        assertEquals(2, game.pannels.size());

        assertNotNull(game.gameFrame);
    }

    @Test
    void MouvementsJ1() {
        //test de mouvemement des personnages
        PlayerPannelModel j1Environnement = (PlayerPannelModel) game.pannels.get(0);
        PlayerModel j1 = j1Environnement.getPlayer();
        //aller à gauche
        int oldPosX = j1.getPosX();
        game.keysPressed.add(j1.getLeft());
        j1.update();
        game.keysPressed.remove(j1.getLeft());
        assertTrue(j1.getPosX() < oldPosX);

        //aller à droite
        oldPosX = j1.getPosX();
        game.keysPressed.add(j1.getRight());

        //on doit attendre 2 update avant d'avoir le changement de direction
        j1.update();
        j1.update();
        j1.update();
        game.keysPressed.remove(j1.getRight());
        assertTrue(j1.getPosX() > oldPosX);

        //test saut
        j1.setCollision(true);
        int oldPosY = j1.getPosY();
        game.keysPressed.add(j1.getUp());
        j1.update();
        game.keysPressed.remove(j1.getUp());
        assertTrue(j1.getPosY() < oldPosY);
        j1.setCollision(false);


    }

    @Test
    void MouvementsJ2() {
        //test de mouvemement des personnages
        PlayerPannelModel j2Environnement = (PlayerPannelModel) game.pannels.get(0);
        PlayerModel j2 = j2Environnement.getPlayer();
        //aller à gauche
        int oldPosX = j2.getPosX();
        game.keysPressed.add(j2.getLeft());
        j2.update();
        game.keysPressed.remove(j2.getLeft());
        assertTrue(j2.getPosX() < oldPosX);

        //aller à droite
        oldPosX = j2.getPosX();
        game.keysPressed.add(j2.getRight());

        //on doit attendre 2 update avant d'avoir le changement de direction
        j2.update();
        j2.update();
        j2.update();
        game.keysPressed.remove(j2.getRight());
        assertTrue(j2.getPosX() > oldPosX);

        //test saut
        j2.setCollision(true);
        int oldPosY = j2.getPosY();
        game.keysPressed.add(j2.getUp());
        j2.update();
        game.keysPressed.remove(j2.getUp());
        assertTrue(j2.getPosY() < oldPosY);
        j2.setCollision(false);

    }

}
