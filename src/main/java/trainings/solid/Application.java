package trainings.solid;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        DataProcessor processor = CommandLineParser.parse(args);

        PlayerRecruiter recruiter = new PlayerRecruiter();
        University university = new University(recruiter);
        university.startAcademicYear();

        CSVImporter importer = new CSVImporter();
        List<Player> players = importer.loadPlayers();

        // recruit new players from the University
        while (recruiter.hasPlayers()) {
            players.add(recruiter.draft());
        }

        if (processor != null) {
            processor.process(players);
        }

        // form a team from players and print its stats:
        Team team = new Team("SOLID", players);
        System.out.println(StatsFormatter.format(team));
        System.out.println("Players:");
        for (Player player : players) {
            System.out.println(StatsFormatter.format(player));
        }

        // export all players to a CSV file:
        CSVExporter csv = new CSVExporter(team.getName());
        csv.write(players);

        university.stopAcademicYear();
    }
}
