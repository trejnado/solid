package trainings.solid;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PlayerRecruiter {

    private List<Player> players = new LinkedList<>();

    public synchronized void addPlayer(Player player) {
        players.add(player);
    }

    public synchronized boolean hasPlayers() {
        return !players.isEmpty();
    }

    /**
     * Return a random player or null if have no players.
     *
     * @return random player or null
     */
    public synchronized Player draft() {
        Collections.shuffle(players);
        return players.isEmpty() ? null : players.remove(0);
    }
}
