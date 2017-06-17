package Uslugi;

import ObjectPlus.ObjectPlus;
import ObjectPlus.ObjectPlusPlus;

import java.util.Vector;

/**
 * Created by ikownacki on 11.06.2017.
 */
public class Faktura extends ObjectPlusPlus {
    private int numerFaktury;

    public Faktura() throws Exception {
        super();
        numerFaktury=getNumerNowejFaktury();
    }

    public int getNumerFaktury() {
        return numerFaktury;
    }

    private int getNumerNowejFaktury() throws Exception {
        Vector<ObjectPlus> faktury = ObjectPlus.dajObiektyZEkstensji(this.getClass());
        int maksNumerFaktury = 0;

        for (ObjectPlus objectPlus : faktury) {
            int tmpNumerFaktury = ((Faktura) objectPlus).getNumerNowejFaktury();
            maksNumerFaktury = tmpNumerFaktury > maksNumerFaktury ? tmpNumerFaktury : maksNumerFaktury;
        }
        return maksNumerFaktury + 1;
    }

    public void dodajUsluge(Usluga usluga){

    }

    public void wystawFakture(){}
}
