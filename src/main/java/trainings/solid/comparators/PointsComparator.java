package trainings.solid.comparators;

import trainings.solid.Player;

import java.util.Comparator;

public class PointsComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return b.getPoints() - a.getPoints();
    }
}
