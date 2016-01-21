package trainings.solid;

public class StatsFormatter {

    public static String format(StatisticalItem player) {
        return String.format("%s points: %2d, assists: %2d",
                player.getDisplayName(), player.getPoints(), player.getAssists());
    }
}
