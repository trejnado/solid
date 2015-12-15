package trainings.solid;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class University {

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final UniCoach coach;

    public University(PlayerRecruiter recruiter) {
        this.coach = new UniCoach(recruiter);
    }

    public void startAcademicYear() {
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                coach.work();
            }
        }, 0, 50, TimeUnit.MILLISECONDS);

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void stopAcademicYear() {
        executor.shutdown();
    }
}
