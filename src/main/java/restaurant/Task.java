package restaurant;

public class Task implements Runnable {
    public void run() {
        this.fire();
    }

    public void fire() {
        System.out.println();
    }
}
