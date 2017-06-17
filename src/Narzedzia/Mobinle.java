package Narzedzia;

import ObjectPlus.ObjectPlusPlus;

/**
 * Created by ikownacki on 11.06.2017.
 */
public class Mobinle extends ObjectPlusPlus{
    int moc;

    private Mobinle(){};

    public int getMoc() {
        return moc;
    }

    public void setMoc(int moc) {
        this.moc = moc;
    }

    public static Mobinle noweMobilne(Narzedzie narzedzie) throws Exception {
        if (narzedzie == null){
            throw new Exception("Brak narzedzia");
        }
        Mobinle mobinle = new Mobinle();
        narzedzie.dodajCzesc("czesc","calosc",mobinle);
        return mobinle;
    }


}
