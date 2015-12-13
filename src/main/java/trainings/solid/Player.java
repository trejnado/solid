package trainings.solid;

public class Player {

    public enum Position { PG, SG, SF, PF, C }

    private String name;
    private byte points;
    private byte assists;
    private Position position;

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", points=" + points +
                ", assists=" + assists +
                '}';
    }
}
