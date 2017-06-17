package Uslugi;

import ObjectPlus.ObjectPlusPlus;

/**
 * Created by ikownacki on 11.06.2017.
 */
public abstract class Usluga extends ObjectPlusPlus {
    private double cena;

    public Usluga() {
        super();
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getCena() {
        return cena;
    }
}
