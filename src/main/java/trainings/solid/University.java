package trainings.solid;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * During academic year the coach starts to train new players
 * and submits them to player recruiter, which will eventually
 * pass them to a professional league.
 */
public class University {

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final UniversityCoach coach;

    public University(PlayerRecruiter recruiter) {
        this.coach = new UniversityCoach(recruiter);
    }

    public void startAcademicYear() {
        makeCoachWork();

        giveCoachTimeToTrainNewPlayers();
    }

    public void stopAcademicYear() {
        executor.shutdown();
    }

    private void makeCoachWork() {
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                coach.work();
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    private void giveCoachTimeToTrainNewPlayers() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
