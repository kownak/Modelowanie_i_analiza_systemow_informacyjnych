package Uslugi;

import java.time.LocalDate;

import static ObjectPlus.ZmienneStatyczne.FAKTURA_CALOSC;
import static ObjectPlus.ZmienneStatyczne.FAKTURA_CZESC;

/**
 * Created by ikownacki on 11.06.2017.
 */
public class Zlecenie extends Usluga {
    private LocalDate terminRealizacji;
    private String opisZlecenia;

    private Zlecenie(double cena, LocalDate terminRealizacji, String opisZlecenia) {
        super();
        this.terminRealizacji = terminRealizacji;
        this.opisZlecenia = opisZlecenia;
    }

    public LocalDate getTerminRealizacji() {
        return terminRealizacji;
    }

    public String getOpisZlecenia() {
        return opisZlecenia;
    }

    @Override
    public double getCena() {
        return super.getCena();
    }

    public static Zlecenie noweZlecenie(Faktura faktura,double cena, LocalDate terminRealizacji, String opisZlecenia) throws Exception {
        if (faktura == null){
            throw new Exception("Brak faktury");
        }
        Zlecenie zlecenie = new Zlecenie(cena,terminRealizacji,opisZlecenia);
        faktura.dodajCzesc(FAKTURA_CALOSC,FAKTURA_CZESC,zlecenie);
        return zlecenie;
    }

}
