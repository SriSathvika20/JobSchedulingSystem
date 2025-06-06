import java.time.LocalDateTime;

public class JobSchedule {

    Job job;
    String type; // "hourly", "daily", "weekly"
    int hour;
    int minute;
    String dayOfWeek; // Only used for weekly, like "monday", "tuesday", etc.

    public JobSchedule(Job job, String type, int hour, int minute, String dayOfWeek) {
        this.job = job;
        this.type = type.toLowerCase();
        this.hour = hour;
        this.minute = minute;
        this.dayOfWeek = dayOfWeek == null ? null : dayOfWeek.toLowerCase();
    }

    public boolean shouldRun(LocalDateTime now) {
        int nowHour = now.getHour();
        int nowMinute = now.getMinute();
        String nowDay = now.getDayOfWeek().toString().toLowerCase(); // e.g., "monday"

        switch (type) {
            case "hourly":
                return nowMinute == minute;
            case "daily":
                return nowHour == hour && nowMinute == minute;
            case "weekly":
                return nowDay.equals(dayOfWeek) && nowHour == hour && nowMinute == minute;
            default:
                return false;
        }
    }

}
