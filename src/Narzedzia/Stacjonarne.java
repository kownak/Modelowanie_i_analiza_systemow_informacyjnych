package Narzedzia;

import ObjectPlus.ObjectPlusPlus;

/**
 * Created by ikownacki on 11.06.2017.
 */
public class Stacjonarne extends ObjectPlusPlus{
    int fizycznyNumerStanowiska;

    private Stacjonarne(int fizycznyNumerStanowiska) {
        this.fizycznyNumerStanowiska = fizycznyNumerStanowiska;
    }

    public int getFizycznyNumerStanowiska() {
        return fizycznyNumerStanowiska;
    }


    public static Stacjonarne noweStacjonarne(Narzedzie narzedzie, int fizycznyNumerStanowiska) throws Exception {
        if(narzedzie ==  null){
            throw new Exception("Brak Narzedzia");
        }
        Stacjonarne stacjonarne = new Stacjonarne(fizycznyNumerStanowiska);
        narzedzie.dodajCzesc("czesc","calosc",stacjonarne);
        return stacjonarne;

    }
}
