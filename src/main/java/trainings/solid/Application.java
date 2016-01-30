package trainings.solid;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Application {

    private final PlayerRecruiter recruiter;
    private final University university;
    private final PlayerImporter importer;
    private final PlayerExporter exporter;
    private final DataProcessor processor;

    public Application(PlayerRecruiter recruiter, University university,
                       PlayerImporter importer, PlayerExporter exporter,
                       DataProcessor processor) {
        this.recruiter = recruiter;
        this.university = university;
        this.importer = importer;
        this.exporter = exporter;
        this.processor = processor;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        DataProcessor processor = CommandLineParser.parse(args);
        PlayerRecruiter recruiter = new PlayerRecruiter();
        University university = new University(recruiter);
        PlayerImporter importer = new CSVImporter();
        PlayerExporter exporter = new CSVExporter();

        Application app = new Application(recruiter, university, importer, exporter, processor);

        app.processPlayers();
    }

    private void processPlayers() throws IOException {
        university.startAcademicYear();

        List<Player> players = importer.loadPlayers();

        recruitPlayers(recruiter, players);

        processor.process(players);

        Team team = new Team("SOLID", players);

        printStats(team);

        exportPlayers(team);

        university.stopAcademicYear();
    }

    private void exportPlayers(Team team) throws IOException {
        exporter.write(team);
    }

    private void printStats(Team team) {
        System.out.println(StatsFormatter.format(team));
        System.out.println("Players:");
        for (Player player : team.getPlayers()) {
            System.out.println(StatsFormatter.format(player));
        }
    }

    private void recruitPlayers(PlayerLottery recruiter, List<Player> players) {
        while (recruiter.hasPlayers()) {
            players.add(recruiter.draft());
        }
    }
}
