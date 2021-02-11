import java.util.Random;
import java.util.concurrent.Semaphore;

public class Claus {

    public int elfCount;
    public int reindeerCount;

    public Semaphore santaSem;
    public Semaphore reindeerSem;
    public Semaphore elfSem;

    public Semaphore mutex;
    public Semaphore elfTex;

    public volatile boolean endOfTheWorld;
    public final Semaphore stopSem;
    public int stopCounter;
    public Random generator;

    public Claus(){

        this.elfCount = 0;
        this.reindeerCount = 0;

        this.santaSem = new Semaphore(0);
        this.reindeerSem = new Semaphore(0, true);
        this.elfSem = new Semaphore(0, true);

        this.mutex = new Semaphore(1, true);
        this.elfTex = new Semaphore(1, true);

        this.endOfTheWorld = false;
        this.stopSem = new Semaphore(0);
        this.stopCounter = 10;
        this.generator = new Random();
    }
}
