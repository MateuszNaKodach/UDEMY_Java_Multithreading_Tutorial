package repeating;

/**
 * Created by Mateusz on 09.05.2017.
 */
public interface Runner {
    void firstThread() throws InterruptedException;

    void secondThread() throws InterruptedException;

    void finished();
}
