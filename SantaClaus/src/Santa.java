import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Santa implements Runnable
{
    private Claus claus;

    public Santa(Claus claus) {
        this.claus = claus;
    }


    @Override
    public void run() {
        while (!claus.endOfTheWorld) {
            try {

                //bir grup elf veya ren geyiği hazır olana kadar bekleyin
                claus.santaSem.acquire();
                // sayaç koru
                claus.mutex.acquire();

                System.out.printf("Noel Baba uyanıyor.\n");

                if (claus.reindeerCount + 1 == 9) {

                    // reındeerCount'u güncelle
                    claus.reindeerCount = 0;

                    // prepsleigh çağır, ren geyikleri bağla
                    prepSleigh();

                    // Kulübe'deki tüm ren geyiklerini uyandır
                    claus.reindeerSem.release(9);

                }
                else if (claus.elfCount == 3) {
                    // Elf grubu için yardım metodu çağor
                    helpElves();
                    // Kapı da bekleyen elfleri uyandır
                    claus.elfSem.release(3);
                }

                claus.mutex.release();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger (Santa.class.getName ()).log (Level.SEVERE, null, ex);
            }

        }
        System.out.println("Noel Baba uyuyor..");
    }


    public void prepSleigh() {
        System.out.print("Noel Baba kızağı hazırlıyor...\n");
        try {
            Thread.sleep(700);
        } catch (InterruptedException ie) {
        }

    }

    public void helpElves() {
        System.out.print("Noel Baba elflere yardım ediyor...\n");
        try {
            Thread.sleep(1100);
        } catch (InterruptedException ie) {
        }

    }
}

