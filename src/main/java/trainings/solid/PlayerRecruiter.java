package trainings.solid;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PlayerRecruiter implements PlayerPool, PlayerLottery {

    private List<Player> players = new LinkedList<>();

    @Override
    public synchronized void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public synchronized boolean hasPlayers() {
        return !players.isEmpty();
    }

    /**
     * Return a random player or null if have no players.
     *
     * @return random player or null
     */
    @Override
    public synchronized Player draft() {
        Collections.shuffle(players);
        return players.isEmpty() ? null : players.remove(0);
    }
}
