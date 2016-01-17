package trainings.solid;

import trainings.solid.Player.Position;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Sorter sorter = CommandLineParser.parse(args);

        PlayerRecruiter recruiter = new PlayerRecruiter();
        University university = new University(recruiter);
        university.startAcademicYear();

        // load professional players from a file
        List<Player> players = new LinkedList<>();
        URI datasetUri = Application.class.getClassLoader().getResource("player-data.txt").toURI();
        for (String line : Files.readAllLines(Paths.get(datasetUri), StandardCharsets.UTF_8)) {
            if (line.startsWith("#")) {
                continue;
            }
            String[] fields = line.split(",");
            Player player = new Player();
            player.setName(fields[0]);
            player.setPosition(Position.valueOf(fields[1].trim()));
            player.setPoints(Byte.valueOf(fields[2].trim()));
            player.setAssists(Byte.valueOf(fields[3].trim()));

            players.add(player);
        }

        // recruit new players from the University
        while (recruiter.hasPlayers()) {
            players.add(recruiter.draft());
        }

        // process players
        if (sorter != null) {
            sorter.sort(players);
        }

        // form a team from players and print its stats:
        Team team = new Team("SOLID", players);
        System.out.printf("%s Team total points: %d, assists: %d%n",
                team.getName(), team.getPoints(), team.getAssists());
        // Instead of logging:
        System.out.println("Players:");
        for (Player player : players) {
            System.out.println(player);
        }

        // export all players to a CSV file:
        CSVExporter csv = new CSVExporter(team.getName());
        csv.write(players);

        university.stopAcademicYear();
    }
}
