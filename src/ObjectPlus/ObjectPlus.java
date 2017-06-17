package ObjectPlus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class ObjectPlus implements Serializable {
    private static Hashtable<Class, Vector<ObjectPlus>> ekstensje = new Hashtable<>();

    public ObjectPlus() {

        Vector<ObjectPlus> ekstensja = null;
        Class klasa = this.getClass();

        if (ekstensje.containsKey(klasa)) {

            ekstensja = ekstensje.get(klasa);

        } else {

            ekstensja = new Vector<>();
            ekstensje.put(klasa, ekstensja);

        }

        ekstensja.add(this);

    }

    public static void zapiszEkstensje(ObjectOutputStream stream) throws IOException {
        stream.writeObject(ekstensje);
    }

    public static void odczytajEkstensje(ObjectInputStream stream) throws IOException {
        try {
            ekstensje = (Hashtable<Class, Vector<ObjectPlus>>) stream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void pokazEkstensje(Class klasa) throws Exception {
        Vector<ObjectPlus> ekstensja = null;

        if (ekstensje.containsKey(klasa)) {
            ekstensja = ekstensje.get(klasa);
        } else {
            throw new Exception("Nieznana klasa" + klasa);
        }
        System.out.println("Ekstensja klasy: " + klasa.getSimpleName());
        for (Object obiekt : ekstensja) {
            System.out.println(obiekt);
        }
    }

    public static Vector<ObjectPlus> dajObiektyZEkstensji(Class klasa) throws Exception {
        Vector<ObjectPlus> ekstensja = null;
        if (ekstensje.containsKey(klasa)) {
            ekstensja = ekstensje.get(klasa);
        } else {
            throw new Exception("Nieznana klasa " + klasa);
        }
        return ekstensja;
    }

    public static boolean czyJestEkstensjaKlasy(Class klasa) {
        return ekstensje.containsKey(klasa);
    }
}

