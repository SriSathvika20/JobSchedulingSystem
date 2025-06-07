import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SchedulerService scheduler = new SchedulerService();

        System.out.println("=== Job Scheduler ===");
        System.out.print("Enter job name: ");
        String jobName = scanner.nextLine();

        System.out.print("Enter schedule type (hourly, daily, weekly): ");
        String type = scanner.nextLine().toLowerCase();

        int hour = 0, minute = 0;
        String dayOfWeek = null;

        switch (type) {
            case "hourly":
                System.out.print("Enter the minute of each hour to run the job (0–59): ");
                minute = scanner.nextInt();
                break;
            case "daily":
                System.out.print("Enter the hour of day to run the job (0–23): ");
                hour = scanner.nextInt();
                System.out.print("Enter the minute (0–59): ");
                minute = scanner.nextInt();
                break;
            case "weekly":
                System.out.print("Enter the day of the week to run the job (e.g., monday): ");
                scanner.nextLine(); // consume newline
                dayOfWeek = scanner.nextLine().toLowerCase();
                System.out.print("Enter the hour (0–23): ");
                hour = scanner.nextInt();
                System.out.print("Enter the minute (0–59): ");
                minute = scanner.nextInt();
                break;
            default:
                System.out.println("Invalid schedule type. Exiting.");
                return;
        }

        Job job = new Job(jobName);
        JobSchedule schedule = new JobSchedule(job, type, hour, minute, dayOfWeek);
        scheduler.addJobSchedule(schedule);

        System.out.println("Job scheduled successfully. Waiting for trigger...");
        scheduler.start();

        // Keep the main thread running to allow the scheduler to keep working
        while (true) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                break;
            }
        }

    }
}
