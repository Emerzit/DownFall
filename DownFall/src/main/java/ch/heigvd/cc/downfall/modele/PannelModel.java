package ch.heigvd.cc.downfall.modele;

import javax.swing.*;

public abstract class PannelModel {
    public abstract void update();
    public abstract void repaint();
    public abstract JPanel getPannel();
}
