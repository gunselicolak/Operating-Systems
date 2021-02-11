import javax.swing.*;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reindeer implements Runnable{

    private Claus claus;
    private int id;

    public Reindeer(Claus claus, int id){
        this.claus = claus;
        this.id = id;
    }

    @Override
    public void run() {
        while (!claus.endOfTheWorld) {
            try {
                Thread.sleep(claus.generator.nextInt(300));
                // sayac koru
                claus.mutex.acquire();

                System.out.println("Reindeer " +id+ " geldi.Kulübe de " + (claus.reindeerCount + 1) + " ren geyiği var\n");

                // reindeerCount arttır
                claus.reindeerCount++;

                if (claus.reindeerCount == 9) {

                    claus.reindeerCount --;
                    // stopCunter azalt
                    claus.stopCounter--;
                    if (claus.stopCounter == 0) {
                        claus.endOfTheWorld = true;
                        claus.stopSem.release();
                    }

                    // santa uyandır
                    claus.santaSem.release();
                    System.out.println("Ren geyiği noel babaya işaret yolluyor.");

                }
                claus.mutex.release();

                // kulübe de bekle
                claus.reindeerSem.acquire();

                // kızağa bağlan metoduna git
                getHitched();

            }
            catch (InterruptedException ie) {
                Logger.getLogger (Reindeer.class.getName ()).log (Level.SEVERE, null, ie);
            }
        }
        System.out.println("Ren geyiği " +id+ " kulübeye gidiyor.");
    }

    private void getHitched() {
        System.out.println("Ren geyiği " +id+ " kızağa bağlanıyor.");
    }
}
