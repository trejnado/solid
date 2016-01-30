package trainings.solid;

import java.io.IOException;
import java.util.List;

public interface PlayerExporter {

    void write(Team team) throws IOException;
}
