package Osoby;

/**
 * Created by ikownacki on 22.04.2017.
 */
public class Kierownik extends Pracownik {

    private Kierownik() {
        super();
    }

    public static Kierownik nowyKierownik(Osoba osoba) throws Exception {
        if(osoba==null){
            throw new Exception("Brak osoby");
        }
        Kierownik kierownik = new Kierownik();
        osoba.dodajCzesc("czesc","calosc",osoba);
        return kierownik;
    }
}
