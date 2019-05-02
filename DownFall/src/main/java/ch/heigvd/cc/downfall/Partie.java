package ch.heigvd.cc.downfall;

public class Partie {
    private PlayerEnvir player1;
    private PlayerEnvir player2;

    Partie(){
        player1 = new PlayerEnvir(new Player("Player1", 0,0));
        player2 = new PlayerEnvir(new Player("Player2", 0,0));
    }

    public PlayerEnvir getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerEnvir player1) {
        this.player1 = player1;
    }

    public PlayerEnvir getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerEnvir player2) {
        this.player2 = player2;
    }
}
