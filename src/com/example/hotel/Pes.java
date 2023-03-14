package com.example.hotel;

public class Pes {

    /** Výchozí barva psa, pokud není uvedena */
    private static final String VYCHOZI_BARVA_PSA = "- barva je tajná -";

    private String jmeno;
    private String barva;
    private int delka;
    private Bouda bouda;

    public Pes(String jmeno, int delka) {
        this(jmeno, delka, VYCHOZI_BARVA_PSA);
    }

    public Pes(String jmeno, int delka, String barva) {
        this.jmeno = jmeno;
        this.barva = barva;
        this.delka = delka;

        // Pes může existovat bez boudy.
        // Do boudy se případně ubytuje až poté, co vznikne.
        this.bouda = null;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getBarva() {
        return barva;
    }

    public void setBarva(String barva) {
        this.barva = barva;
    }

    public int getDelka() {
        return delka;
    }

    public void setDelka(int delka) {
        this.delka = delka;
    }

    public Bouda getBouda() {
        return bouda;
    }

    public void setBouda(Bouda bouda) {
        this.bouda = bouda;
    }

    public boolean isUbytovany () {

        // Pokud bouda není inicializována hodnotou (je null),
        // pak pes není ubytován
        return null != bouda;
    }

    public int getObjemBoudy () {
        return bouda.getObjem();
    }

    public String zastekej () {

        // Zde se metoda toString() zavolá automaticky při konverzi objektu Pes na String k vypsání
        System.out.println(this);

        // Zde navrátím explicitně výsledek metody toString(), protože v tomto případě
        // se automatická konverze neprovede - překladač na toto upozorní ještě před spuštěním kódu
        return toString();
    }

    @Override
    public String toString() {
        return "Já jsem pes " + jmeno + ". Haf haf!";
    }
}
