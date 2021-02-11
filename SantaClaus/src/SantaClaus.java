import java.io.IOException;
import java.util.HashSet;


public class SantaClaus {

    private  int id;

    public void startProgram() {

        Claus claus = new Claus();

        Thread santaThread = new Thread(new Santa(claus));

        Thread[] elvesThread = new Thread[10];

        Thread[] reindeerThread = new Thread[9];

        santaThread.start();

        for (int i= 0; i < elvesThread.length; i++)
        {
            elvesThread[i] = new Thread(new Elves(claus,id++));

        }

        for (int i= 0; i<elvesThread.length; i++)
        {
            elvesThread[i].start();
        }

        for (int i =0; i<reindeerThread.length; i++)
        {
            reindeerThread[i] = new Thread(new Reindeer(claus,id++));
        }

        for (int i= 0; i<reindeerThread.length; i++)
        {
            reindeerThread[i].start();
        }


    }

    public static void main(String[] args) throws IOException {
        new SantaClaus().startProgram();
    }
}
