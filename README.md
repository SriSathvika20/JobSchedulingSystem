This project is a simple **Job Scheduling System in Java** that allows users to schedule tasks to run at specific times. Users can define a job schedule as **hourly**, **daily**, or **weekly** through
console input. Once the user provides the schedule details, the system continuously checks the current time and automatically triggers the job to print `"Hello World"` based on the user's input.
The scheduling system uses `ScheduledExecutorService` to efficiently manage time-based task execution, and the program stays running to ensure scheduled jobs are executed on time.
