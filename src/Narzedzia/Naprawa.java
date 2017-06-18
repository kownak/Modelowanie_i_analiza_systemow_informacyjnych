package Narzedzia;

import ObjectPlus.ObjectPlusPlus;

import java.time.LocalDate;

/**
 * Created by ikownacki on 11.06.2017.
 */
public class Naprawa extends ObjectPlusPlus{
    private LocalDate dataZleceniaNaprawy;
    private LocalDate dataZakonczeniaNaprawy;
    private double koszt;

    public Naprawa() {
        this.dataZleceniaNaprawy = LocalDate.now();

    }

    public double getKoszt() {
        return koszt;
    }

    public void setKoszt(double koszt) {
        this.koszt = koszt;
    }

    public LocalDate getDataZleceniaNaprawy() {
        return dataZleceniaNaprawy;
    }

    public void setDataZleceniaNaprawy(LocalDate dataZleceniaNaprawy) {
        this.dataZleceniaNaprawy = dataZleceniaNaprawy;
    }

    public LocalDate getDataZakonczeniaNaprawy() {
        return dataZakonczeniaNaprawy;
    }

    public void setDataZakonczeniaNaprawy(LocalDate dataZakonczeniaNaprawy) {
        this.dataZakonczeniaNaprawy = dataZakonczeniaNaprawy;
    }



}
