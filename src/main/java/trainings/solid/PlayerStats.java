package trainings.solid;

import trainings.solid.comparators.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.sort;


public class PlayerStats {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Player> players = new LinkedList<>();
        URI datasetUri = PlayerStats.class.getClassLoader().getResource("player-data.txt").toURI();
        for (String line : Files.readAllLines(Paths.get(datasetUri), StandardCharsets.UTF_8)) {
            if (line.startsWith("#")) {
                System.out.println(line);
                continue;
            }
            String[] fields = line.split(",");
            Player player = new Player();
            player.setName(fields[0]);
            player.setPoints(Byte.valueOf(fields[1].trim()));
            player.setAssists(Byte.valueOf(fields[2].trim()));

            players.add(player);
        }

        //System.out.println("Players: " + players);

        if (args.length == 2 && args[0].equals("sort")) {
            System.out.println("Sorting by: " + args[1]);
            switch (args[1]) {
                case "name":
                    sort( players, new NameComparator() );
                    break;
                case "points":
                    sort( players, new PointsComparator() );
                    break;
                case "assist":
                    sort( players, new AssistsComparator());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown option: " + args[1]);
            }
        } // pick best by X

        System.out.println("Players:");
        for (Player p : players) {
            System.out.println(p);
        }
    }
}
