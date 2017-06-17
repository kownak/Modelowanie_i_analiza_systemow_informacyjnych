package Osoby;

import ObjectPlus.ObjectPlusPlus;
import ObjectPlus.ZmienneStatyczne;
import Uslugi.Faktura;

/**
 * Created by ikownacki on 10.06.2017.
 */
public class Klient extends ObjectPlusPlus implements ZmienneStatyczne{
    private double rabat =0;
    private Klient() {
        super();
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public static Klient utworzKlienta(Osoba osoba) throws Exception {
        if (osoba == null){
            throw new Exception("Brak Osoby");
        }
        Klient klient = new Klient();
        osoba.dodajCzesc(KLIENT_CZESC,KLIENT_CALOSC,klient);
        return klient;
    }

    public void dodajFakture(Faktura faktura){
        this.dodajPowiazanie(FAKTURY,KLIENCI,faktura,faktura.getNumerFaktury());
    }
}
