package trainings.solid;

import java.util.List;

public class Best5Processor implements DataProcessor {

    private final String field;

    public Best5Processor(String field) {
        this.field = field;
    }

    @Override
    public void process(List<Player> players) {
        DataProcessor sorter = new Sorter(field);
        sorter.process(players);
        players.subList(5, players.size()).clear();
    }
}
