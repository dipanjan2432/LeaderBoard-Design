package controller;

import Utils.KeyUtils;
import model.Game;
import model.LeaderBoard;
import model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLeaderBoardRepository implements LeaderBoardRepository {

    Map<String, Game> games = new HashMap<>();
    Map<String, LeaderBoard> leaderBoards = new HashMap<>();

    @Override
    public List<String> getSupportedGames() {
        return games.keySet().stream().toList();
    }

    @Override
    public String createLeaderBoard(String gameId, int startEpochSeconds, int endEpochSeconds) throws RuntimeException {
        String leaderBoardId = KeyUtils.getLeaderBoardId(gameId, startEpochSeconds, endEpochSeconds);
        // Check if game preset else create game.
        if (!games.containsKey(gameId)) {
            games.put(gameId, new Game(gameId));
        }
        Game game = games.get(gameId);

        // Check if leaderBoard is preset else create leaderBoard.
        if (!game.isLeaderBoardPreset(leaderBoardId)) {
            game.addLeaderBoard(leaderBoardId);
            leaderBoards.put(leaderBoardId, new LeaderBoard(leaderBoardId, startEpochSeconds, endEpochSeconds));
        } else {
            throw new RuntimeException("LeaderBoard Already preset");
        }

        return leaderBoardId;
    }

    @Override
    public List<Player> getLeaderBoard(String leaderBoardID) throws NullPointerException {
        if (leaderBoards.containsKey(leaderBoardID)) {
            return leaderBoards.get(leaderBoardID).getLeaderBoard();
        }
        throw new NullPointerException("LeaderBoard not present");
    }

    @Override
    public void submitScore(String gameId, String userId, int score) {
        if (games.containsKey(gameId)) {
            for (String leaderBoardId : games.get(gameId).getLeaderBoards()) {
                LeaderBoard board = leaderBoards.get(leaderBoardId);
                if (board.getEndTime() > getCurrentTimeStampSeconds()) {
                    board.submitScore(userId, score);
                }
            }
        } else  {
            throw new NullPointerException("Game not present");
        }
    }

    @Override
    public List<Player> listPlayersNext(String gameId, String leaderBoardId, String playerId, int nPlayers) {
        if (leaderBoards.containsKey(leaderBoardId)) {
            return leaderBoards.get(leaderBoardId).getPlayerNext(playerId, nPlayers);
        }
        throw new NullPointerException("Leaderboard not present");
    }

    @Override
    public List<Player> listPlayersPrev(String gameId, String leaderBoardId, String playerId, int nPlayers) {
        if (leaderBoards.containsKey(leaderBoardId)) {
            return leaderBoards.get(leaderBoardId).getPlayerPrev(playerId, nPlayers);
        }
        throw new NullPointerException("Leaderboard not present");
    }

    private int getCurrentTimeStampSeconds() {
        return (int) System.currentTimeMillis() / 1000;
    }
}
