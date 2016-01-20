package trainings.solid;

import trainings.solid.Player.Position;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UniversityCoach {

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private Random random = new Random();

    private PlayerRecruiter recruiter;

    public UniversityCoach(PlayerRecruiter recruiter) {
        this.recruiter = recruiter;
    }

    public void startWork() {
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                work();
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void stopWork() {
        executor.shutdown();
    }

    private void work() {
        Player player = new Player();
        player.setName("Random Joe #" + random.nextInt(100));
        player.setPoints((byte) random.nextInt(50));
        player.setPosition(Position.values()[random.nextInt(Position.values().length)]);
        player.setAssists((byte) random.nextInt(20));
        recruiter.addPlayer(player);
    }
}
