package trainings.solid;

public class StatsFormatter {

    public static String format(Player player) {
        if (player instanceof Team) {
            return  String.format("%s Team total points: %d, assists: %d",
                    player.getName(), player.getPoints(), player.getAssists());
        }
        return String.format("%-15s(%2s), points: %2d, assists: %2d",
                player.getName(), player.getPosition(), player.getPoints(),
                player.getAssists());
    }
}
