package trainings.solid;

import java.util.List;

public class Team extends Player {

    private final List<Player> players;

    public Team(String name, List<Player> players) {
        setName(name);
        this.players = players;
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
    public void setPoints(int points) {
        throw new UnsupportedOperationException("Team has player points!");
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
    public void setAssists(int assists) {
        throw new UnsupportedOperationException("Team has player assists!");
    }

    @Override
    public Position getPosition() {
        throw new UnsupportedOperationException("Team has no position!");
    }

    @Override
    public void setPosition(Position position) {
        throw new UnsupportedOperationException("Team has no position!");
    }

    @Override
    public String toString() {
        return "Team(" + getName() + "):" + players;
    }
}
