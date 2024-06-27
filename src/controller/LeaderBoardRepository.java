package controller;

import model.Player;

import java.util.List;

public interface LeaderBoardRepository {

    List<String> getSupportedGames();

    String createLeaderBoard(String gameId, int startEpochSeconds, int endEpochSeconds) throws RuntimeException;

    List<Player> getLeaderBoard(String leaderBoardID) throws NullPointerException;

    void submitScore(String gameId, String userId, int score) throws NullPointerException;

    List<Player> listPlayersNext(String gameId, String leaderBoardId, String playerId, int nPlayers) throws NullPointerException;

    List<Player> listPlayersPrev(String gameId, String leaderBoardId, String playerId, int nPlayers);


}
