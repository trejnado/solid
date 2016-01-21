package trainings.solid;

public class Player extends StatisticalItem {

    @Override
    public String getDisplayName() {
        return String.format("%-15s(%2s)", getName(), getPosition());
    }

    public enum Position { PG, SG, SF, PF, C }

    private Position position;

    public void setPoints(int points) {
        this.points = points;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
