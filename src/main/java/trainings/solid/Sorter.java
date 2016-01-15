package trainings.solid;

import trainings.solid.comparators.AssistsComparator;
import trainings.solid.comparators.NameComparator;
import trainings.solid.comparators.PointsComparator;

import java.util.List;

import java.util.Collections;

public class Sorter {

    private String sortBy;

    public Sorter(String sortBy) {
        this.sortBy = sortBy;
    }

    public void sort(List<Player> players) {
        System.out.println("Sorting by: " + sortBy);
        switch (sortBy) {
            case "name":
                Collections.sort(players, new NameComparator() );
                break;
            case "points":
                Collections.sort(players, new PointsComparator() );
                break;
            case "assist":
                Collections.sort(players, new AssistsComparator());
                break;
            default:
                throw new IllegalArgumentException("Unknown option: " + sortBy);
        }
    }
}
