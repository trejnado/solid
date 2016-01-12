package trainings.solid;

import trainings.solid.Player.Position;

import java.util.Random;

public class UniversityCoach {

    private Random random = new Random();

    private PlayerRecruiter recruiter;

    public UniversityCoach(PlayerRecruiter recruiter) {
        this.recruiter = recruiter;
    }

    public void work() {
        Player player = new Player();
        player.setName("Random Joe #" + random.nextInt(100));
        player.setPoints((byte) random.nextInt(50));
        player.setPosition(Position.values()[random.nextInt(Position.values().length)]);
        player.setAssists((byte) random.nextInt(20));
        recruiter.addPlayer(player);
    }
}
