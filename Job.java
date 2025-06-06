import java.time.LocalDateTime;

public class Job {

    private final String name;

    public Job(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("[" + LocalDateTime.now() + "] " + name );//+ ": Hello World");
    }

}
