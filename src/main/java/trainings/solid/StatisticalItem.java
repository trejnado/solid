package trainings.solid;

public abstract class StatisticalItem {

    protected String name;
    protected int points;
    protected int assists;

    public abstract String getDisplayName();

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getAssists() {
        return assists;
    }

    public void setName(String name) {
        this.name = name;
    }
}
