package trainings.solid;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Load professional players from a file.
 */
public class CSVImporter {

    public List<Player> loadPlayers() throws URISyntaxException, IOException {
        List<Player> players = new LinkedList<>();
        URI datasetUri = Application.class.getClassLoader().getResource("player-data.txt").toURI();
        for (String line : Files.readAllLines(Paths.get(datasetUri), StandardCharsets.UTF_8)) {
            if (line.startsWith("#")) {
                continue;
            }
            String[] fields = line.split(",");
            Player player = new Player();
            player.setName(fields[0]);
            player.setPosition(Player.Position.valueOf(fields[1].trim()));
            player.setPoints(Byte.valueOf(fields[2].trim()));
            player.setAssists(Byte.valueOf(fields[3].trim()));

            players.add(player);
        }
        return players;
    }
}
