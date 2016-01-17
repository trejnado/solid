package trainings.solid;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CSVExporter {
    private final Path file;

    public CSVExporter(String filename) throws IOException {
        file = Files.createTempFile(filename, ".csv");
    }

    public void write(List<Player> players) {
        try (BufferedWriter csv = Files.newBufferedWriter(file, UTF_8)) {
            csv.write("#name,position,points,assists\n");
            for (Player player : players) {
                csv.write(player.getName() + "," + player.getPosition() +
                        "," + player.getPoints() + "," + player.getAssists()
                        + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
