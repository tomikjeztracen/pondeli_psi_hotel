import com.example.hotel.Bouda;
import com.example.hotel.Pes;
import com.example.hotel.PsiHotel;
import com.example.hotel.StandardniBouda;

public class Application {
    public static void main (String[] args) {

        // Vytvořím psí hotel
        PsiHotel hotel = new PsiHotel();

        // Nastavím maximální objem pro boudy na 18m3
        hotel.setMaximalniObjemProBoudy(18);

        // Ubytovaní psy v hotelu:
        hotel.vypisBoudySePsi(); // Prázdné

        // Ubytování psa
        Pes karel = new Pes("Karel", 1);
        hotel.ubytujPsa(karel);

        Pes jiri = new Pes("Jiří", 2);
        hotel.ubytujPsa(jiri);

        //  Jiří i Karel by nyní měli mít vlastní boudu:
        for (Pes pes: new Pes[]{karel, jiri}) {
            System.out.println("Je " + pes.getJmeno() + " ubytovaný v boudě? " + pes.isUbytovany());
        }

        // Pokusím se ubytovat příliš velkého psa do příliš malé boudy:
        Bouda bouda = new StandardniBouda();
        Pes jaromir = new Pes("Jaromír", 3);
        hotel.ubytujPsa(jaromir, bouda);    // Po doplnění kontrol (viz TO-DO) vrátí false
                                            // a Jaromíra neubytuje, protože je moc velký

        // Je hotel obsazený boudami? Tzn. je prostor pro boudy vyčerpán?
        System.out.println("Je hotel obsazený boudami? " + hotel.isObsazeno()); // false

        // Přidám do zásoby pár psích boud:
        hotel.pridejBoudu(new StandardniBouda());   // true
        hotel.pridejBoudu(new StandardniBouda());   // true
        hotel.pridejBoudu(new StandardniBouda());   // true
        hotel.pridejBoudu(new StandardniBouda());   // true
        hotel.pridejBoudu(new StandardniBouda());   // true
        hotel.pridejBoudu(new StandardniBouda());   // true
        hotel.pridejBoudu(new StandardniBouda());   // vrátí true / false v závislosti na ubytování psa Jaromíra
        hotel.pridejBoudu(new StandardniBouda());   // false - tato bouda už se do hotelu nevejde
                                                    // (nutné implementovat podmínku v TO-DO)
        hotel.pridejBoudu(new StandardniBouda());   // false - tato bouda už se do hotelu nevejde
                                                    // (nutné implementovat podmínku v TO-DO)
        hotel.pridejBoudu(new StandardniBouda());   // false - tato bouda už se do hotelu nevejde
                                                    // (nutné implementovat podmínku v TO-DO)

        // Obsadím volné boudy pár psi:
        hotel.ubytujPsa(new Pes("Erben", 1)); // vrátí true
        hotel.ubytujPsa(new Pes("Hyne", 1)); // vrátí true
        hotel.ubytujPsa(new Pes("Mácha", 2)); // vrátí true
        hotel.ubytujPsa(new Pes("Vodník", 2)); // vrátí true

        // TODO Naplňte hotel dále různými boudami (vyměnte standardní boudu za
        //  sdílenou / luxusní) a psi tak, aby se do něj už žádný další pes
        //  ubytovat nemohl, tzn. aby metoda hotel.ubytujPsa() vrátila hodnotu false.

        // výpis všech psů
        hotel.vypisBoudySePsi();
    }
}
