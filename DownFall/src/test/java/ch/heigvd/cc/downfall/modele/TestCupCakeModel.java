package ch.heigvd.cc.downfall.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCupCakeModel {
    private CupCakeModel cL;
    private CupCakeModel cR;

    @BeforeEach
    void setUp() {
        cL = new CupCakeModel(false, 0, 0);
        cR = new CupCakeModel(true, 0, 0);
    }


    @Test
    void Deplacement() {
        assertTrue(cL.getVectX() < 0);
        assertTrue(cR.getVectX() > 0);

        //déplacement du bon coté

        //à gauche
        cL.update();

        assertTrue(cL.getPosX() < 0);
        assertTrue(!(cL.getPosY() < 0));

        // à droite
        cR.update();
        assertTrue(cR.getPosX() > 0);
        assertTrue(!(cL.getPosY() < 0));
    }
    @Test
    void Explosion() {

        //pas explosé en faisant rien
        assertFalse(cL.getIsExploding());
        cL.setCollision(true);
        //pas explosé tant qu'il n'y a pas eu de mise à jour
        assertFalse(cL.getIsExploding());
        cL.update();
        //en train d'exploser après mise à jour
        assertTrue(cL.getIsExploding());
        //attendre qu'il est fini d'exploser avant de voir qu'il est détruit
        while (cL.getCupCakeImg().getCupCakeImg().size()-1 > cL.imgpos) {
            assertFalse(cL.isDestroyed());
            cL.update();
        }
        assertTrue(cL.isDestroyed());
    }
}
