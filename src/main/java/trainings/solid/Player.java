package trainings.solid;

public class Player {

    private String name;
    private byte points;
    private byte rebounds;
    private byte assists;
    private byte steals;

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

    public byte getRebounds() {
        return rebounds;
    }

    public void setRebounds(byte rebounds) {
        this.rebounds = rebounds;
    }

    public byte getAssists() {
        return assists;
    }

    public void setAssists(byte assists) {
        this.assists = assists;
    }

    public byte getSteals() {
        return steals;
    }

    public void setSteals(byte steals) {
        this.steals = steals;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", rebounds=" + rebounds +
                ", assists=" + assists +
                ", steals=" + steals +
                '}';
    }
}
