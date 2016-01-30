package trainings.solid;

import java.io.IOException;
import java.net.URISyntaxException;

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

        app.processTeam();
    }

    private void processTeam() throws IOException {
        university.startAcademicYear();

        Team team = loadTeam("SOLID");

        recruitPlayers(team);

        processor.process(team);

        printStats(team);

        export(team);

        university.stopAcademicYear();
    }

    private void export(Team team) throws IOException {
        exporter.write(team);
    }

    private Team loadTeam(String name) {
        return new Team(name, importer.loadPlayers());
    }

    private void printStats(Team team) {
        System.out.println(StatsFormatter.format(team));
        System.out.println("Players:");
        for (Player player : team.getPlayers()) {
            System.out.println(StatsFormatter.format(player));
        }
    }

    private void recruitPlayers(Team team) {
        while (recruiter.hasPlayers()) {
            team.getPlayers().add(recruiter.draft());
        }
    }
}
