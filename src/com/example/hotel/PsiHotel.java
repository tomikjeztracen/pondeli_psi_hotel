package com.example.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PsiHotel {
    private int maximalniObjemProBoudy = 20;

    private List<Bouda> boudy = new ArrayList<>();

    public int getMaximalniObjemProBoudy() {
        return maximalniObjemProBoudy;
    }

    public void setMaximalniObjemProBoudy(int maximalniObjemProBoudy) {
        this.maximalniObjemProBoudy = maximalniObjemProBoudy;
    }

    /**
     * Přidá do hotelu psí boudu, v případě, že se objemem
     * ještě vejde
     *
     * @param bouda Bouda k přidání do psího hotelu
     * @return Vešla se bouda objemem do hotelu (tzn. byla přidána)?
     */
    public boolean pridejBoudu (Bouda bouda) {

        // TODO do výrazu vejdeSe patří kontrola, zdali se bouda vejde do hotelu
        boolean vejdeSe = true; // Upravit na vhodný výraz (hint: využijte metody, které máte k dispozici)

        if ( vejdeSe ) {
            boudy.add(bouda);
        }

        return vejdeSe;
    }

    /**
     * Odebere boudu z hotelu
     *
     * @param bouda Bouda k odebrání
     * @return Povedlo se odebrání boudy?
     */
    public boolean odeberBoudu (Bouda bouda) {

        // TODO zkontrolovat, zdali boudu z hotelu neodebíráme i se psi.
        // Výraz musí být upraven tak, aby vracel true v případě,
        // že v boudě bouda není žádný pes
        boolean boudaJePrazdna = true; // Upravit na vhodný výraz

        // Pokud bouda není prázdná, tak ji nelze odebrat -
        // navrátím false a ukončím běh metody
        if (!boudaJePrazdna) {
            return false;
        }

        // Zde si postačím s tímto výrazem, protože metoda remove, jak lze vyčíst z dokumentace,
        // sama vrací hodnotu true v případě, že kolekce prvek obsahovala
        return boudy.remove(bouda);
    }

    /**
     * Všichni psi ubytovaní v hotelu zaštěkají
     */
    public void zastekejteVsichniPsi () {
        // Projdu všechny boudy v hotelu
        for (Bouda bouda: boudy) {

            // A všechny psy v těchto boudách
            for (Pes pes: bouda.getUbytovaniPsi()) {

                // Každý pes zaštěká
                pes.zastekej();
            }
        }
    }

    /**
     * @return Je hotel plný psích boud?
     */
    public boolean isObsazeno () {

        // Zjistím celkový objem všech přítomných psích boud
        int vyuzityObjem = 0;
        for (Bouda bouda: boudy) {
            vyuzityObjem += bouda.getObjem();
        }

        // Dostačuje maximální objem pro boudy k přidání další boudy?
        // Dle zadání má nejmenší bouda objem 2
        return vyuzityObjem + 2 > maximalniObjemProBoudy;
    }

    /**
     * Ubytuje psa, přičemž pes (či majitel) není vybíravý a spokojí se s libovolnou
     * boudou, která ještě má volnou kapacitu, nebo s výchozí boudou typu StandardniBouda
     *
     * @param pes Pes, který má být ubytován. V hotelu smí být ubytován pouze jednou
     * @return Povedlo se ubytovat psa?
     */
    public boolean ubytujPsa (Pes pes) {
        // Není-li specifikována bouda, vyberu první boudu, která má volnou kapacitu
        // a psa ubytuji tam
        Bouda volnaBouda = null;

        for (Bouda bouda: boudy) {
            if (!bouda.isObsazeno()) {
                volnaBouda = bouda;
                break; // Vyskočí z cyklu for a pokračuje v běhu programu
            }
        }

        // Pokud byla nalezena volná bouda, pokusím se v ní psa ubytovat
        boolean pesUbytovan = false;
        if (null != volnaBouda) {
            pesUbytovan = ubytujPsa(pes, volnaBouda);
        }

        if (pesUbytovan) {
            // Zdařilo-li se ubytování psa, navrátím true a končím běh metody
            return true;
        }

        // Pokud se nepovedlo psa z jakéhokoliv důvodu ubytovat,
        // pokusím se mu vytvořit výchozí boudu StandardniBouda
        Bouda vychoziBouda = new StandardniBouda();
        boolean boudaPridana = pridejBoudu(vychoziBouda);

        // Zdařilo se přidat další výchozí boudu do psího hotelu?
        if (!boudaPridana) {
            // Ne, hotel je plný, psa nemohu ubytovat, vracím false
            return false;
        }

        // Pokusím se do této boudy psa ubytovat a výsledek navrátím
        return ubytujPsa(pes, vychoziBouda);
    }

    /**
     * Ubytuje psa pes v boudě bouda.
     *
     * @param pes Pes, který má být ubytován. V hotelu smí být ubytován pouze jednou
     * @param bouda Bouda musí v hotelu existovat a nesmí být plně obsazena
     * @return Povedlo se ubytování?
     */
    public boolean ubytujPsa (Pes pes, Bouda bouda) {
        // Není už tento pes v hotelu ubytován?
        // Dopomohu si soukromou metodou ubytovavaPsa() - není v zadání,
        // je tomuto kódu pouze pomocná a zvnější objektu neviditelná
        boolean jeUbytovan = ubytovavaPsa(pes);

        if (jeUbytovan) {
            // Psa lze v hotelu ubytovat jen jednou,
            // tedy toto ubytování se nezdařilo
            return false;
        }

        // Existuje tato bouda v hotelu?
        // TODO doplnit výraz tak, aby navrátil boolean hodnotu dle toho, zdali je bouda v hotelu již umístěna
        boolean maHotelBoudu = true;

        if (!maHotelBoudu) {
            // V hotelu musí bouda existovat, ale neexistuje,
            // tedy toto ubytování se nezdařilo
            return false;
        }

        // Má bouda ještě volnou kapacitu?
        boolean jeBoudaObsazena = bouda.isObsazeno();

        if (jeBoudaObsazena) {
            // Tato bouda je již plně obsazená,
            // tedy toto ubytování se nezdařilo
            return false;
        }

        // Všechny podmínky jsou splněny, psa se mohu pokusit ubytovat v boudě
        return bouda.pridejPsa(pes);
    }

    /**
     * @param pes Ubytovává hotel tohoto psa?
     * @return Je pes "pes" ubytován v hotelu?
     */
    private boolean ubytovavaPsa (Pes pes) {
        for (Bouda bouda: boudy) {
            if (bouda.ubytovavaPsa(pes)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Vypíše na standardní výstup strukturovaně celý obsah psího hotelu
     */
    public void vypisBoudySePsi () {
        for (Bouda bouda: boudy) {
            Set<Pes> ubytovaniPsi = bouda.getUbytovaniPsi();

            System.out.print("Bouda typu " + bouda.getClass());
            System.out.print(", ubytováno " + ubytovaniPsi.size() + " psů:\n");

            for (Pes pes: ubytovaniPsi) {
                System.out.println("  - " + pes);
            }
        }
    }
}
