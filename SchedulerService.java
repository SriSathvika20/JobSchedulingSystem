import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerService {

    private final List<JobSchedule> schedules = new ArrayList<>();

    public void addJobSchedule(JobSchedule schedule) {
        schedules.add(schedule);
    }

    public void start() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
            for (JobSchedule schedule : schedules) {
                if (schedule.shouldRun(now)) {
                    schedule.job.run();
                }
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

}
