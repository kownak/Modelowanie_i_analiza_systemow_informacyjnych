package Osoby;

import ObjectPlus.ObjectPlusPlus;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * Created by ikownacki on 22.04.2017.
 */
public class Pracownik extends ObjectPlusPlus {

    private static double pensjaPodstawowa = 2000; //atrybut klasowy
    private LocalDate dataZatrudnienia; //atrybut zlozony
    private Date dataZwolnienia; //atrybut zlozony opcjonalny
    private double pensja;


    protected Pracownik( ) {
        super();
        this.dataZatrudnienia = LocalDate.now();
        this.pensja = pensjaPodstawowa;

    }

    public void setDataZwolnienia(Date dataZwolnienia) {
        this.dataZwolnienia = dataZwolnienia;
    }

    public void setPensja(double nowaPensja) { //metoda przeciazenie
        pensja = nowaPensja;
    }

    public void setPensja(double stawkaGodzinowa, int dniPracujace) { //metoda przeciazenie
        pensja = stawkaGodzinowa * 8 * dniPracujace;
    }

    public double getPensja() {
        return pensja;
    }

    public int getStazPracy() { //atrybut pochodny
        LocalDate obecnaData = LocalDate.now();
        Period p = Period.between(dataZatrudnienia, obecnaData);
        return p.getYears() * 12 + p.getMonths();
    }

    public String getDataZwolnienia() {
        String zwolnienie = dataZwolnienia == null ? "Nie zwolniono" : dataZwolnienia.toString();
        return zwolnienie;
    }

    public static Pracownik nowyPracownik(Osoba osoba) throws Exception {
        if (osoba == null){
            throw new Exception("Brak osoby");
        }
        Pracownik pracownik = new Pracownik();
        osoba.dodajCzesc("czesc","calosc",pracownik);
        return pracownik;
    }

}
