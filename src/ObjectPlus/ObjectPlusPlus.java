package ObjectPlus;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * Klasa ulatwiajaca zarzadzanie asocjacjami (powiazaniami). Poniewaz dziedziczy z ObjectPlus ulatiwa takze zarzadzanie ekstensja.
 * @author MariuszAdmin
 *
 */
public class ObjectPlusPlus extends ObjectPlus implements Serializable {
    /**
     * Przechowuje informacje o wszystkich powiazaniach tego obiektu.
     */
    private Hashtable<String, HashMap<Object, ObjectPlusPlus>> powiazania = new Hashtable<String, HashMap<Object, ObjectPlusPlus>>();

    /**
     * Przechowuje informacje o wszystkich czesciach powiazanych z ktorymkolwiek z obiektow.
     */
    private static HashSet<ObjectPlusPlus> wszystkieCzesci = new HashSet<ObjectPlusPlus>();

    /**
     * Konstruktor.
     *
     */
    public ObjectPlusPlus() {
        super();
    }

    private void dodajPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy, Object kwalifikator, int licznik) {
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu;

        // Zabezpieczenie dla tworzenia polaczenia zwrotnego
        if(licznik < 1) {
            return;
        }

        // Znajdz kolekcje powiazan dla tej roli
        if(powiazania.containsKey(nazwaRoli)) {
            // Pobierz te powiazania
            powiazaniaObiektu = powiazania.get(nazwaRoli);
        }
        else {
            // Brak powiazan dla takiej roli ==> utworz
            powiazaniaObiektu = new HashMap<Object, ObjectPlusPlus>();
            powiazania.put(nazwaRoli, powiazaniaObiektu);
        }

        // Sprawdz czy powiazanie juz istnieje?
        // Jezeli tak to zignoruj dodawanie
        if(!powiazaniaObiektu.containsKey(kwalifikator)) {
            // Dodaj powiazanie dla tego obiektu
            powiazaniaObiektu.put(kwalifikator, obiektDocelowy);

            // Dodaj powiazanie zwrotne
            obiektDocelowy.dodajPowiazanie(odwrotnaNazwaRoli, nazwaRoli, this, this, licznik - 1);
        }
    }

    /**
     * Tworzy nowe powi�zanie do podanego obiektu (ewentualnie w ramach asocjacji kwalifikowanej).
     * @param nazwaRoli
     * @param odwrotnaNazwaRoli
     * @param obiektDocelowy
     * @param kwalifikator Jezeli rozny od null to tworzona jest asocjacja kwalifikowana.
     */
    public void dodajPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy, Object kwalifikator) {
        dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy, kwalifikator, 2);
    }

    /**
     * Tworzy nowe powi�zanie do podanego obiektu (jako zwyk�� asocjacj� binarn�. NIE jako kwalifikowan�).
     * @param nazwaRoli
     * @param odwrotnaNazwaRoli
     * @param obiektDocelowy
     */
    public void dodajPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy) {
        dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy, obiektDocelowy);
    }

    /**
     * Dodaje informacje o powiazaniu z czescia (jako kompozycja).
     * Sprawdza czy dodawana czesc nie jest juz polaczona z caloscia.
     * @param nazwaRoli
     * @param odwrotnaNazwaRoli
     * //@param obiektDocelowy
     * @throws Exception
     */
    public void dodajCzesc(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektCzesc) throws Exception {
        // Sprawdz czy ta czesc juz gdzies nie wystepuje
        if(wszystkieCzesci.contains(obiektCzesc)) {
            throw new Exception("Ta czesc jest ju� powiazana z jakas caloscia!");
        }

        dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektCzesc);

        // Zapamietaj dodanie obiektu jako czesci
        wszystkieCzesci.add(obiektCzesc);
    }

    /**
     * Zwraca tablice zawierajaca docelowe obiekty dla podanej nazwy roli.
     * @param nazwaRoli
     * @return
     * @throws Exception
     */
    public ObjectPlusPlus[] dajPowiazania(String nazwaRoli) throws Exception {
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu;

        if(!powiazania.containsKey(nazwaRoli)) {
            // Brak powiazan dla tej roli
            throw new Exception("Brak powiazan dla roli: " + nazwaRoli);
        }

        powiazaniaObiektu = powiazania.get(nazwaRoli);

        return (ObjectPlusPlus[]) powiazaniaObiektu.values().toArray(new ObjectPlusPlus[0]);
    }

    /**
     * Informuje czy istnieja powiazania dla podanej nazwy roli.
     * @param nazwaRoli
     * @return false gdy brak nazwy roli lub gdy liczba powiazan dla tej roli jest 0.
     */
    public boolean czySaPowiazania(String nazwaRoli) {
        if(!powiazania.containsKey(nazwaRoli)) {
            return false;
        }

        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu = powiazania.get(nazwaRoli);
        return powiazaniaObiektu.size() > 0;
    }

    /**
     * Wyswietla powiazania (dla podanej nazwy roli) dla podanym strumieniu.
     * @param nazwaRoli
     * @param stream
     * @throws Exception
     */
    public void wyswietlPowiazania(String nazwaRoli, PrintStream stream) throws Exception {
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu;

        if(!powiazania.containsKey(nazwaRoli)) {
            // Brak powiazan dla tej roli
            throw new Exception("Brak powiazan dla roli: " + nazwaRoli);
        }

        powiazaniaObiektu = powiazania.get(nazwaRoli);

        Collection col = powiazaniaObiektu.values();

        stream.println(this.getClass().getSimpleName() + " powiazania w roli " + nazwaRoli + ":");

        for(Object obj : col) {
            stream.println("   " + obj);
        }
    }

    /**
     * Zwraca obiekt odnaleziony na podstawie kwalifikatora (dla podanej nazwy roli).
     * Dziala w oparciu o asocjacje kwalifikowana.
     * @param nazwaRoli
     * @param kwalifikator
     * @return
     * @throws Exception
     */
    public ObjectPlusPlus dajPowiazanyObiekt(String nazwaRoli, Object kwalifikator) throws Exception {
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu;

        if(!powiazania.containsKey(nazwaRoli)) {
            // Brak powiazan dla tej roli
            throw new Exception("Brak powiazan dla roli: " + nazwaRoli);
        }

        powiazaniaObiektu = powiazania.get(nazwaRoli);
        if(!powiazaniaObiektu.containsKey(kwalifikator)) {
            // Brak powiazan dla tej roli
            throw new Exception("Brak powiazania dla kwalifikatora: " + kwalifikator);
        }

        return powiazaniaObiektu.get(kwalifikator);
    }

    public void usunPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy, Object kwalifikator, int licznik){
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu;

        if(licznik < 1) {
            return;
        }

        // Znajdz kolekcje powiazan dla tej roli
        if(powiazania.containsKey(nazwaRoli)) {
            // Pobierz te powiazania
            powiazaniaObiektu = powiazania.get(nazwaRoli);
        }
        else {
            return;
        }

        // Sprawdz czy powiazanie juz istnieje?
        // Jezeli tak to zignoruj dodawanie
        if(powiazaniaObiektu.containsKey(kwalifikator)) {
            // Dodaj powiazanie dla tego obiektu
            powiazaniaObiektu.remove(kwalifikator, obiektDocelowy);

            // Dodaj powiazanie zwrotne
            obiektDocelowy.usunPowiazanie(odwrotnaNazwaRoli, nazwaRoli, this, this, licznik - 1);
        }
    }

    public void usunPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy, Object kwalifikator) {
        usunPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy, kwalifikator, 2);
    }
    public void usunPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy) {
        usunPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy, obiektDocelowy);
    }


}
