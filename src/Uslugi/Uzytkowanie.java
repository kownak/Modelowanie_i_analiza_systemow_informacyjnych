package Uslugi;

import Narzedzia.Narzedzie;
import ObjectPlus.ZmienneStatyczne;

import java.time.LocalDate;

/**
 * Created by ikownacki on 11.06.2017.
 */
public class Uzytkowanie extends Usluga{
    private LocalDate dataPoczatku;
    private LocalDate dataKonca;

    public Uzytkowanie(double cena, LocalDate dataPoczatku, LocalDate dataKonca, Narzedzie narzedzie) {
        super();
        super.setCena(cena);
        this.dataPoczatku = dataPoczatku;
        this.dataKonca = dataKonca;
        this.dodajPowiazanie("narzedzia","uzytkowanie",narzedzie);
        narzedzie.setStatus(ZmienneStatyczne.ZAJETE);
    }

    public LocalDate getDataPoczatku() {
        return dataPoczatku;
    }

    public void setDataPoczatku(LocalDate dataPoczatku) {
        this.dataPoczatku = dataPoczatku;
    }

    public LocalDate getDataKonca() {
        return dataKonca;
    }

    public void setDataKonca(LocalDate dataKonca) {
        this.dataKonca = dataKonca;
    }

    @Override
    public void setCena(double cena) {
        super.setCena(cena);
    }

    @Override
    public double getCena() {
        return super.getCena();
    }



}
