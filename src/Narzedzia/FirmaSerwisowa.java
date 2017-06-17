package Narzedzia;

import ObjectPlus.ObjectPlusPlus;

/**
 * Created by ikownacki on 11.06.2017.
 */
public class FirmaSerwisowa extends ObjectPlusPlus {
    private String nazwa;
    private String adres;
    private String telefon;
    private String numerKonta;

    public FirmaSerwisowa(String nazwa, String adres, String telefon, String numerKonta) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.telefon = telefon;
        this.numerKonta = numerKonta;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNumerKonta() {
        return numerKonta;
    }

    public void setNumerKonta(String numerKonta) {
        this.numerKonta = numerKonta;
    }
}
