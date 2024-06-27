package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    String gameId;
    Set<String> leaderBoardIds;

    public Game(String gameId){
        this.gameId = gameId;
        this.leaderBoardIds = new HashSet<>();
    }

    public Boolean isLeaderBoardPreset(String leaderBoardId){
        return leaderBoardIds.contains(leaderBoardId);
    }

    public void addLeaderBoard(String leaderBoardId) {
        leaderBoardIds.add(leaderBoardId);
    }

    public List<String> getLeaderBoards(){
        return  leaderBoardIds.stream().toList();
    }
}
