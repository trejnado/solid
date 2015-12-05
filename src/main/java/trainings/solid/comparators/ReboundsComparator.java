package trainings.solid.comparators;

import trainings.solid.Player;

import java.util.Comparator;

public class ReboundsComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return b.getRebounds() - a.getRebounds();
    }
}
