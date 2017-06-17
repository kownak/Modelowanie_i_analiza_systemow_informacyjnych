package ObjectPlus;

/**
 * Created by ikownacki on 11.06.2017.
 */
public interface ZmienneStatyczne {
    //statusy dal narzedzi
    int DOSTEPNE = 0;
    int ZAJETE = 1;
    int ZEPSUTE = 2;
    int NAPRAWIANE = 3;

    //role w asocjacjach
    String NAPRAWIANE_NARZEDZIE = "naprawianeNarzedzie";
    String FIRMA_SERWISOWA = "firmaSerwisowa";
    String NAPRAWA = "naprawa";
    String KLIENT_CZESC = "klientCzesc";
    String KLIENT_CALOSC = "klientCalosc";
    String FAKTURY = "faktury";
    String KLIENCI = "klienci";
    String KLIENT_POSIADAJACY = "klientPosiadajacy";
    String NARZEDZIE_U_KLIENTA = "narzedzieUKlienta";
}
