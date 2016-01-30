package trainings.solid;

import trainings.solid.comparators.AssistsComparator;
import trainings.solid.comparators.NameComparator;
import trainings.solid.comparators.PointsComparator;

import java.util.*;

public class Sorter implements DataProcessor {

    private Map<String, Comparator<Player>> fields = new HashMap<>();

    {
        fields.put("name", new NameComparator());
        fields.put("points", new PointsComparator());
        fields.put("assist", new AssistsComparator());
    }

    private String sortBy;

    public Sorter(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public void process(Team team) {
        System.out.println("Sorting by: " + sortBy);
        Comparator<Player> comparator = fields.get(sortBy);
        if (comparator == null) {
            throw new IllegalArgumentException("Unknown option: " + sortBy);
        }
        Collections.sort(team.getPlayers(), comparator);
    }
}
