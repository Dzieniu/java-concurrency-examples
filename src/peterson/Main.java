package peterson;

public class Main {

    private static boolean[] zainteresowany = {false, false};
    private static volatile int czyja_kolej = 1;
    private static volatile int tura = 0;
    private static volatile int licznik = 0;

    public static void main(String[] args) {

        (new Thread(new Process1())).start();
        (new Thread(new Process2())).start();
    }

    public static class Process1 implements Runnable{
        @Override
        public void run() {
            while (true) {
                zainteresowany[0] = true;
                czyja_kolej = 1;
                while (zainteresowany[1]  && (czyja_kolej == 1)){
                    // Czekaj
                }
                // Sekcja krytyczna
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){}
                tura++;
                licznik++;
                System.out.println("Tura: " + tura + " , zwiekszono licznik: "
                        + licznik);
                // koniec sekcji krytycznej
                zainteresowany[0] = false;
            }
        }
    }

    public static class Process2 implements Runnable{
        @Override
        public void run() {
            while (true) {
                zainteresowany[1] = true;
                czyja_kolej = 0;
                while (zainteresowany[0]  && (czyja_kolej == 0)){
                    // Czekaj
                }
                // Sekcja krytyczna
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
                tura++;
                licznik--;
                System.out.println("Tura: " + tura + " , zmniejszono licznik: "
                        + licznik);
                // koniec sekcji krytycznej
                zainteresowany[1] = false;
            }
        }
    }
}
