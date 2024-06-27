package Utils;

public class KeyUtils {
    private  static String UNDER_SCORE = "_";
    public static String getLeaderBoardId(String gameId, int startTimeSeconds, int endTimeSeconds){
        return gameId + UNDER_SCORE + startTimeSeconds + "_" + UNDER_SCORE;
    }
}
