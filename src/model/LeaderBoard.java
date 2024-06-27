package model;

import java.util.*;
import java.util.stream.Collectors;

public class LeaderBoard {
    // Sorted set custom comparator for value comparison;
    SortedSet<Map.Entry<String, Integer>> sortedSet = new TreeSet<>((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
    private String leaderBoardId;
    private int startTimeSecs;
    private int endTimeSecs;
    private TreeMap<String, Integer> scoresMap = new TreeMap<>();


    public LeaderBoard(String leaderBoardId, int startTimeSecs, int endTimeSecs) {
        this.leaderBoardId = leaderBoardId;
        this.startTimeSecs = startTimeSecs;
        this.endTimeSecs = endTimeSecs;
        this.scoresMap = new TreeMap<>();
    }

    public void submitScore(String userId, int score) {
        if (scoresMap.containsKey(userId) && scoresMap.get(userId) >= score) {
            return;
        }
        scoresMap.put(userId, score);
        sortedSet.add(Map.entry(userId, score));
    }

    public List<Player> getLeaderBoard() {
        List<Player> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedSet) {
            list.add(new Player(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    public int getEndTime() {
        return endTimeSecs;
    }

    public List<Player> getPlayerNext(String playerId, int nPlayer) throws NullPointerException {
        if (scoresMap.containsKey(playerId)) {
            Integer score = scoresMap.get(playerId);
            return sortedSet.tailSet(Map.entry(playerId, score)).stream().limit(nPlayer + 1).map(e -> new Player(e.getKey(), e.getValue())).collect(Collectors.toList());
        }
        throw new NullPointerException("Player not found");
    }

    public List<Player> getPlayerPrev(String playerId, int nPlayer) throws NullPointerException {
        if (scoresMap.containsKey(playerId)) {
            Integer score = scoresMap.get(playerId);
            return sortedSet.headSet(Map.entry(playerId, score)).stream().limit(nPlayer + 1).map(e -> new Player(e.getKey(), e.getValue())).collect(Collectors.toList());
        }
        throw new NullPointerException("Player not found");
    }

}
