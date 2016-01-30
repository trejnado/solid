package trainings.solid;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Application {

    private final PlayerRecruiter recruiter;
    private final University university;
    private final PlayerImporter importer;
    private final DataProcessor processor;

    public Application(PlayerRecruiter recruiter, University university,
                       PlayerImporter importer, DataProcessor processor) {
        this.recruiter = recruiter;
        this.university = university;
        this.importer = importer;
        this.processor = processor;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        DataProcessor processor = CommandLineParser.parse(args);
        PlayerRecruiter recruiter = new PlayerRecruiter();
        University university = new University(recruiter);
        PlayerImporter importer = new CSVImporter();

        Application app = new Application(recruiter, university, importer, processor);

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

    private PlayerExporter createExporter(Team team) throws IOException {
        return new CSVExporter(team.getName());
    }

    private void exportPlayers(Team team) throws IOException {
        PlayerExporter exporter = createExporter(team);
        exporter.write(team.getPlayers());
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
