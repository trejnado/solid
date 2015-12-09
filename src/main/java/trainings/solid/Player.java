package trainings.solid;

public class Player {

    private String name;
    private byte points;
    private byte assists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getPoints() {
        return points;
    }

    public void setPoints(byte points) {
        this.points = points;
    }

    public byte getAssists() {
        return assists;
    }

    public void setAssists(byte assists) {
        this.assists = assists;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", assists=" + assists +
                '}';
    }
}
