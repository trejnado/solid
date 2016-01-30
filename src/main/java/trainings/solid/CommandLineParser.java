package trainings.solid;

public class CommandLineParser {

    private static class NoopProcessor implements DataProcessor {

        public static DataProcessor INSTANCE = new NoopProcessor();

        @Override
        public void process(Team team) {
            // does nothing
        }
    }

    public static DataProcessor parse(String[] args) {
        //System.out.println("Players: " + players);
        if (args.length == 2 && args[0].equals("sort")) {
            // sort players by selected field:
            return new Sorter(args[1]);
        } else if (args.length == 2 && args[0].equals("best")) {
            return new Best5Processor(args[1]);
        }
        return NoopProcessor.INSTANCE;
    }
}
