package trainings.solid;

import java.util.concurrent.TimeUnit;

/**
 * During academic year the coach starts to train new players
 * and submits them to player recruiter, which will eventually
 * pass them to a professional league.
 */
public class University {

    private final UniversityCoach coach;

    public University(PlayerRecruiter recruiter) {
        this.coach = new UniversityCoach(recruiter);
    }

    public void startAcademicYear() {
        coach.startWork();

        giveCoachTimeToTrainNewPlayers();
    }

    public void stopAcademicYear() {
        coach.stopWork();
    }

    private void giveCoachTimeToTrainNewPlayers() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
