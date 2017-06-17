package Uslugi;

import java.time.LocalDate;

/**
 * Created by ikownacki on 11.06.2017.
 */
public class Zlecenie extends Usluga {
    private LocalDate terminRealizacji;
    private String opisZlecenia;

    public Zlecenie(double cena, LocalDate terminRealizacji, String opisZlecenia) {
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

}
