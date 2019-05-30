package ch.heigvd.cc.downfall.modele;

import ch.heigvd.cc.downfall.view.Background;
import ch.heigvd.cc.downfall.view.MenuPanel;

public class MenuPanelModel extends PannelModel {
    private Background bg;
    private MenuPanel menuPanel;

    private String[] options = {
            "Start",
            "Options",
            "Quit"
    };

    private int currentChoice = 0;

    MenuPanelModel(){
        menuPanel = new MenuPanel(options);
        this.bg = new Background("/Backgrounds/menubg.gif");
        bg.setVector(-0.5, -0.5);
        menuPanel.setBg(bg);
    }

    public void update() {

    }

    public void repaint() {
        menuPanel.repaint();
    }
}
