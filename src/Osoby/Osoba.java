package Osoby;

import ObjectPlus.ObjectPlusPlus;

/**
 * Created by ikownacki on 22.04.2017.
 */
public class Osoba extends ObjectPlusPlus {
    protected String imie;
    protected String nazwisko;
    protected String pesel;
    protected String telefon;
    protected String adresZamieszkania;


    private Osoba(String imie, String nazwisko, String pesel, String telefon, String adresZamieszkania) {
        super();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.telefon = telefon;
        this.adresZamieszkania = adresZamieszkania;
    }


    @Override
    public String toString() {
        return imie + " " + nazwisko + " " + pesel;
    }

    @Override
    public boolean equals(Object obj) {
        Osoba osoba = (Osoba) obj;
        if (imie.equals(osoba.imie) &&
                nazwisko.equals(osoba.nazwisko) &&
                pesel.equals(osoba.pesel)
            // TODO: 16.06.2017 porownanie czesci
                ) {
            return true;
        }
        return false;
    }

    public static Osoba nowyKlient(String imie, String nazwisko, String pesel, String telefon, String adresZamieszkania) {
        Osoba osoba = new Osoba(imie, nazwisko, pesel, telefon, adresZamieszkania);
        try {
            Klient klient = Klient.utworzKlienta(osoba);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return osoba;
    }

    public static Osoba nowyPracownik(String imie, String nazwisko, String pesel, String telefon, String adresZamieszkania) {
        Osoba osoba = new Osoba(imie, nazwisko, pesel, telefon, adresZamieszkania);
        try {
            Pracownik pracownik = Pracownik.nowyPracownik(osoba);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return osoba;
    }

    public static Osoba nowyKierownik(String imie, String nazwisko, String pesel, String telefon, String adresZamieszkania) {
        Osoba osoba = new Osoba(imie, nazwisko, pesel, telefon, adresZamieszkania);
        try {
            Kierownik kierownik = Kierownik.nowyKierownik(osoba);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return osoba;
    }
}
