package com.example.hotel;

import java.util.HashSet;
import java.util.Set;

public class StandardniBouda implements Bouda {

    private int vyska = 1;
    private int sirka = 1;
    private int hloubka = 2;
    private int kapacita = 1;

    private Set<Pes> ubytovani = new HashSet<>();

    @Override
    public int getKapacita() {
        return kapacita;
    }

    @Override
    public boolean isObsazeno() {
        // Kapacita
        int kapacita = this.kapacita;

        // Obsazenost boudy
        int obsazenost = this.ubytovani.size();

        // nemám boudu plnou?
        // Povšimněte si, že úplně postačí navrátit výraz "obsazenost >= kapacita",
        // protože tato podmínka ve skutečnosti vypadá: if (true) { return true; } else { return false; }
        if (obsazenost >= kapacita) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean pridejPsa(Pes pes) {

        // Kontrola, zdali se pes do boudy ještě vejde
        // Je kapacita boudy dostatečná k ubytování dalšího psa?
        boolean jeBoudaObsazena = isObsazeno();

        if (jeBoudaObsazena) {
            // Bouda je již obsazena, přidání psa se nezdařilo
            return false;
        }

        // Není pes "pes" již v této boudě přítomen?
        // TODO doplnit následující výraz tak aby vracel true v případě, kdy pes "pes" je v této boudě již přítomen
        boolean jePesJizVBoude = false;

        if (jePesJizVBoude) {
            // Pes "pes" už v této boudě je, nemůže v ní být dvakrát
            return false;
        }

        // TODO zde obdobně jako výše v této metodě zkontrolovat,
        //  zdali se pes "pes" svou délkou do boudy alespoň v jednom horizontálním
        //  rozměru (šířka či hloubka boudy) vejde.
        //  Pokud ne, nemůže být v této boudě ubytován a metoda navrátí false.
        //  Dále by bylo vhodné zkontrolovat, zdali se do boudy vejde i se psi,
        //  kteří v ní již ubytováni jsou.

        // Všechny podmínky jsou splněny, přidávám psa do boudy
        ubytovani.add(pes);

        // Abych měl referenci na tuto boudu i v opačném směru, tedy od psa, přidám ji i k psovi
        pes.setBouda(this);

        // Zadařilo se, navracím true
        return true;
    }

    @Override
    public boolean odeberPsa(Pes pes) {

        // Tato podmínka je ve skutečnosti zbytečná,
        // protože metoda remove vrací boolean hodnotu v závislosti na tom,
        // zdali kolekce prvek před odstraněním obsahovala
        if (this.ubytovavaPsa(pes)) {
            return this.ubytovani.remove(pes);
        }

        return false;
    }

    @Override
    public boolean ubytovavaPsa(Pes pes) {
        return this.ubytovani.contains(pes);
    }

    @Override
    public Set<Pes> getUbytovaniPsi() {
        return this.ubytovani;
    }

    @Override
    public int getObjem() {
        return vyska * sirka * hloubka;
    }

    public int getVyska() {
        return vyska;
    }

    public int getSirka() {
        return sirka;
    }

    public int getHloubka() {
        return hloubka;
    }
}
