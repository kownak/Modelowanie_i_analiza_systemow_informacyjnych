package Narzedzia;

import ObjectPlus.*;
import Osoby.Kierownik;
import Osoby.Osoba;

import java.util.Date;
import java.util.Vector;

/**
 * Created by ikownacki on 11.06.2017.
 */
public class Narzedzie extends ObjectPlusPlus implements ZmienneStatyczne {
    private String nazwaPotoczna;
    private String pelnaNazwa;
    private Date dataZakupu;
    private boolean czySprawne;
    private int status;

    private Narzedzie(String nazwaPotoczna, String pelnaNazwa, Date dataZakupu) {
        this.nazwaPotoczna = nazwaPotoczna;
        this.pelnaNazwa = pelnaNazwa;
        this.dataZakupu = dataZakupu;
        czySprawne = true;
    }

    public String getNazwaPotoczna() {
        return nazwaPotoczna;
    }

    public void setNazwaPotoczna(String nazwaPotoczna) {
        this.nazwaPotoczna = nazwaPotoczna;
    }

    public String getPelnaNazwa() {
        return pelnaNazwa;
    }

    public void setPelnaNazwa(String pelnaNazwa) {
        this.pelnaNazwa = pelnaNazwa;
    }

    public Date getDataZakupu() {
        return dataZakupu;
    }

    public void setDataZakupu(Date dataZakupu) {
        this.dataZakupu = dataZakupu;
    }

    public boolean isCzySprawne() {
        return czySprawne;
    }

    public void setCzySprawne(boolean czySprawne) {
        this.czySprawne = czySprawne;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void oznaczJakoZepsute() {
        this.setStatus(ZEPSUTE);
    }

    public void przekazDoNaprawy(Kierownik kierownik, FirmaSerwisowa firmaSerwisowa) {
        Naprawa naprawa = new Naprawa();
        firmaSerwisowa.dodajPowiazanie(NAPRAWA, FIRMA_SERWISOWA, naprawa);
        this.setStatus(NAPRAWIANE);
    }

    public void oznaczJakoDostepne() {
        this.setStatus(DOSTEPNE);
    }

    public void wypozycz(Osoba osoba) throws Exception {
        if (osoba.czySaPowiazania(KLIENCI)){
            throw new Exception("Osoba musi być klientem ");
        }
        osoba.dodajPowiazanie(NARZEDZIE_U_KLIENTA,KLIENT_POSIADAJACY,this);
        this.setStatus(ZAJETE);
    }

    public void zwroc(Osoba osoba) throws Exception {
        if (osoba.czySaPowiazania(KLIENCI)){
            throw new Exception("Osoba musi być klientem ");
        }
        osoba.usunPowiazanie(NARZEDZIE_U_KLIENTA,KLIENT_POSIADAJACY,this);
    }


    public static Vector<Narzedzie> dajWszystkieDlaStatusu(int status) {
        try {
            Vector<Narzedzie> narzedzia = new Vector<>();

            for (ObjectPlus o : ObjectPlus.dajObiektyZEkstensji(Narzedzie.class)) {
                Narzedzie narzedzie = (Narzedzie) o;

                if (narzedzie.getStatus() == status) {
                    narzedzia.add(narzedzie);
                }
            }
            return narzedzia;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Narzedzie noweMobilne(String nazwaPotoczna, String pelnaNazwa, Date dataZakupu, Integer moc) {
        Narzedzie narzedzie = new Narzedzie(nazwaPotoczna, pelnaNazwa, dataZakupu);
        try {
            Mobinle mobinle = Mobinle.noweMobilne(narzedzie);
            if (moc != null) {
                mobinle.setMoc(moc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return narzedzie;
    }

    public static Narzedzie noweStacjonarne(String nazwaPotoczna, String pelnaNazwa, Date dataZakupu,
                                            int fizycznyNumerStanowiska) {
        Narzedzie narzedzie = new Narzedzie(nazwaPotoczna, pelnaNazwa, dataZakupu);
        try {
            Stacjonarne stacjonarne = Stacjonarne.noweStacjonarne(narzedzie, fizycznyNumerStanowiska);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return narzedzie;
    }

    @Override
    public String toString() {
        return nazwaPotoczna + " " + pelnaNazwa;
    }
}

