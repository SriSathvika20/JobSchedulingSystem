import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SchedulerService {

    private final List<JobSchedule> schedules = new ArrayList<>();

    public void addJobSchedule(JobSchedule schedule) {
        schedules.add(schedule);
    }

    public void start() {
        Runnable schedulerTask = () -> {
            LocalDateTime lastChecked = null;

            while (true) {
                LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

                // To prevent double triggering within the same minute
                if (lastChecked == null || !now.equals(lastChecked)) {
                    for (JobSchedule schedule : schedules) {
                        if (schedule.shouldRun(now)) {
                            schedule.job.run();
                        }
                    }
                    lastChecked = now;
                }

                try {
                    Thread.sleep(1000); // Check every second to avoid missing the minute mark
                } catch (InterruptedException e) {
                    System.out.println("Scheduler stopped.");
                    break;
                }
            }
        };

        Thread schedulerThread = new Thread(schedulerTask);
        schedulerThread.start();
    }

}
