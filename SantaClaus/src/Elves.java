import java.util.logging.Level;
import java.util.logging.Logger;

public class Elves implements Runnable {

    private Claus claus;
    private int id;

    public Elves(Claus claus,int id) {
        this.claus = claus;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(claus.generator.nextInt(500));
                while (!claus.endOfTheWorld) {

                    claus.elfTex.acquire();
                    System.out.printf("\n Elf " +id+" geldi. Kapıda " +(claus.elfCount+1)+ " elf var.\n");


                    claus.mutex.acquire();
                    claus.elfCount++;
                    if (claus.elfCount == 3) {
                        // Noel Baba' uyandır, elfTex açık tut
                        // diğer elfler giremez
                        // mevcut grup girebilir
                        // noel babadan yardım alabilir
                        getHelp();
                        claus.santaSem.release();
                    } else {
                        // bekleyen diğer elflere işaret verilir
                        // sıraya katılabilmeleri için
                        claus.elfTex.release();
                    }

                    claus.mutex.release();

                    //bir grup elf tamamlanana kadar bekle
                    claus.elfSem.acquire();

                    // noel baba dan yardım iste
                    //getHelp();

                    claus.mutex.acquire();
                    // elfCount azalt
                    claus.elfCount--;

                    if (claus.elfCount == 0) {
                        // son elf çıkışlarından sonra, elf muteksini serbest bırakın
                        // böylece diğer elfler katılabilir
                        claus.elfTex.release();
                    }
                    claus.mutex.release();
                }
            }
            catch (InterruptedException e) {
            }
            System.out.printf("Elf " +id+ " ayrıldı\n");
        }
    }

    private void getHelp() {
        System.out.printf("Elfler yardım bekliyor...\n");
    }
}
