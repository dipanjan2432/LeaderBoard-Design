package model;

public class Player {
    private String playerId;
    private long score;

    Player(String playerId, long score){
        this.playerId = playerId;
        this.score = score;
    }

    @Override
    public String toString() {
        return "PlayerId:" + playerId + " Score: " + score;
    }
}
