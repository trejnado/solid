package trainings.solid;

import java.util.List;

public class Team extends StatisticalItem {

    private final List<Player> players;

    public Team(String name, List<Player> players) {
        setName(name);
        this.players = players;
    }

    @Override
    public String getDisplayName() {
        return String.format("%s Team", getName());
    }

    @Override
    public int getPoints() {
        int sum = 0;
        for (Player player : players) {
            sum += player.getPoints();
        }
        return sum;
    }

    @Override
    public int getAssists() {
        int sum = 0;
        for (Player player : players) {
            sum += player.getAssists();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Team(" + getName() + "):" + players;
    }
}
