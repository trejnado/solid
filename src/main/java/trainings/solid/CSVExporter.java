package trainings.solid;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CSVExporter implements PlayerExporter {
    private Path file;

    @Override
    public void write(Team team) throws IOException {
        createFile(team.getName());
        try (BufferedWriter csv = Files.newBufferedWriter(file, UTF_8)) {
            csv.write("#name,position,points,assists\n");
            for (Player player : team.getPlayers()) {
                csv.write(player.getName() + "," + player.getPosition() +
                        "," + player.getPoints() + "," + player.getAssists()
                        + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFile(String filename) throws IOException {
        file = Files.createTempFile(filename, ".csv");
    }
}
