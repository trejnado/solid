package trainings.solid;

public class Best5Processor implements DataProcessor {

    private final String field;

    public Best5Processor(String field) {
        this.field = field;
    }

    @Override
    public void process(Team team) {
        DataProcessor sorter = new Sorter(field);
        sorter.process(team);
        team.getPlayers().subList(5, team.getPlayers().size()).clear();
    }
}
