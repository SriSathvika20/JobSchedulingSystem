import java.time.LocalDateTime;

public class JobSchedule {

    Job job;
    String type; // "hourly", "daily", "weekly"
    int hour;
    int minute;
    String dayOfWeek; // Used only for weekly

    public JobSchedule(Job job, String type, int hour, int minute, String dayOfWeek) {
        this.job = job;
        this.type = type.toLowerCase();
        this.hour = hour;
        this.minute = minute;
        this.dayOfWeek = (dayOfWeek != null) ? dayOfWeek.toLowerCase() : null;
    }

    public boolean shouldRun(LocalDateTime now) {
        int currentHour = now.getHour();
        int currentMinute = now.getMinute();
        String currentDay = now.getDayOfWeek().toString().toLowerCase();

        if (type.equals("hourly")) {
            return currentMinute == minute;
        } else if (type.equals("daily")) {
            return currentHour == hour && currentMinute == minute;
        } else if (type.equals("weekly")) {
            return currentDay.equals(dayOfWeek) && currentHour == hour && currentMinute == minute;
        }
        return false;
    }

}

