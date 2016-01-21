package trainings.solid;

public class CommandLineParser {

    public static DataProcessor parse(String[] args) {
        //System.out.println("Players: " + players);
        if (args.length == 2 && args[0].equals("sort")) {
            // sort players by selected field:
            return new Sorter(args[1]);
        } else if (args.length == 2 && args[0].equals("best")) {
            return new Best5Processor(args[1]);
        }
        return null;
    }
}
