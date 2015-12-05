package trainings.solid.comparators;

import trainings.solid.Player;

import java.util.Comparator;

public class AssistsComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return b.getAssists() - a.getAssists();
    }
}
