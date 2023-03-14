package com.example.hotel;

import java.util.Set;

/**
 * TODO Přidat rozhraní Searchable a odkomentovat řádek 8
 */
public interface Bouda /* extends Searchable */ {
    public int getKapacita ();
    public boolean isObsazeno ();
    public boolean pridejPsa (Pes pes);
    public boolean odeberPsa (Pes pes);
    public boolean ubytovavaPsa (Pes pes);
    public Set<Pes> getUbytovaniPsi ();
    public int getObjem ();
}
