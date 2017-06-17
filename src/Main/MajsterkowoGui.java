package Main;

import Narzedzia.Narzedzie;
import Osoby.Osoba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import ObjectPlus.*;


/**
 * Created by ikownacki on 11.06.2017.
 */
public class MajsterkowoGui extends JFrame implements ZmienneStatyczne {
    private static final String MOBILNE = "mobilne";
    private static final String STACJONARNE = "stacjonarne";

    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JList<Osoba> listaKlientow;
    private JButton dodajKlientaButton;
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JTextField nrTelefonuTextField;
    private JTextField peselTextField;
    private JTextField adresTextField;
    private JList<Narzedzie> listaNarzedziUKlienta;
    private JButton zwrocButton;
    private JPanel panelZKlientami;
    private JPanel panelDodawaniaKlienta;
    private JPanel panelOperacjiNarzedziUKlienta;
    private JPanel panelNarzedziUKlienta;
    private JLabel nazwaKlienta;
    private JList<Narzedzie> listaNarzedzi;
    private JPanel panelNarzedzi;
    private JPanel panelOperacjiNarzedzi;
    private JButton dodajNarzedziebutton;
    private JRadioButton mobilneRadioButton;
    private JRadioButton stacjonarneRadioButton;
    private JTextField nazwaPotocznaTextField;
    private JTextField pelnaNazwaTextField;
    private JTextField dataZakupuTextField;
    private JTextField mocTextField;
    private JTextField fizycznyNrStanowiskaTextField;
    private JLabel nazwaPosiadajacegoNarzedzie;
    private JButton wypozyczButton;
    private JLabel wybranyKlientNarzedziaJLabel;
    private JLabel mocJLabel;
    private JLabel fizycznyNrStanowiskaJLabel;
    private JLabel wybranyKlientKlienciJLabel;
    private JLabel wybraneNarzedzieNarzedzia;

    private ButtonGroup grupaRadioButton = new ButtonGroup();
    private DefaultListModel<Osoba> listaKlientowModel = new DefaultListModel<>();
    private DefaultListModel<Narzedzie> listaNarzedziModel = new DefaultListModel<>();
    private DefaultListModel<Narzedzie> listaNarzedziUKlientaModel = new DefaultListModel<>();


    public MajsterkowoGui(String ekstensjaPath) {
        super();
        try {
            setContentPane(tabbedPane1);

            setDodajKlientaButtonActionListener();
            ekstensjaTrwaloscOdczys(ekstensjaPath);
            setListaKlientowINarzedziModeler();
            setRadioButtonsActionsListeners();
            setDodajNarzedziebuttonActioListenet();
            setListaKlientowSelectionListener();
            setWypozyczButtonActionListener();
            setListaNarzedziSelectionListener();

            pack();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {

                    ekstensjaTrwaloscZapis(ekstensjaPath);

                }
            });
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ekstensjaTrwaloscOdczys(String path) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            ObjectPlus.odczytajEkstensje(objectInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ekstensjaTrwaloscZapis(String path) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            ObjectPlus.zapiszEkstensje(objectOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDodajKlientaButtonActionListener() {
        dodajKlientaButton.addActionListener(a -> {
            String imie = imieTextField.getText();
            String nazwisko = nazwiskoTextField.getText();
            String nrTelefonu = nrTelefonuTextField.getText();
            String pesel = peselTextField.getText();
            String adres = adresTextField.getText();

            imieTextField.setText("");
            nazwiskoTextField.setText("");
            nrTelefonuTextField.setText("");
            peselTextField.setText("");
            adresTextField.setText("");


            Osoba osoba = Osoba.nowyKlient(imie, nazwisko, pesel, nrTelefonu, adres);
            if (listaKlientowModel.contains(osoba)) {
                // TODO: 16.06.2017 usuniecie osoby z ekstensji
                return;
            }
            listaKlientowModel.addElement(osoba);

        });
    }

    private void setWypozyczButtonActionListener() {
        wypozyczButton.setEnabled(false);
        wypozyczButton.addActionListener(a -> {
            Osoba klient = listaKlientow.getSelectedValue();
            Narzedzie narzedzie = listaNarzedzi.getSelectedValue();

            try {
                narzedzie.wypozycz(klient);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void setZwrocButtonActionListener(){
        zwrocButton.setEnabled(false);

        zwrocButton.addActionListener(a ->{
            Narzedzie narzedzie = listaNarzedziUKlienta.getSelectedValue();
            Osoba klient = listaKlientow.getSelectedValue();
            listaNarzedziUKlientaModel.removeElement(narzedzie);

            try {
                narzedzie.zwroc(klient);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }


    private void setRadioButtonsActionsListeners() {
        grupaRadioButton.add(mobilneRadioButton);
        mobilneRadioButton.setActionCommand(MOBILNE);

        grupaRadioButton.add(stacjonarneRadioButton);
        stacjonarneRadioButton.setActionCommand(STACJONARNE);

        mobilneRadioButton.setSelected(true);
        fizycznyNrStanowiskaTextField.setEnabled(false);
        fizycznyNrStanowiskaJLabel.setForeground(Color.GRAY);

        mobilneRadioButton.addActionListener(a -> {
            if (mobilneRadioButton.isSelected()) {
                fizycznyNrStanowiskaTextField.setEnabled(false);
                fizycznyNrStanowiskaTextField.setText("");
                fizycznyNrStanowiskaJLabel.setForeground(Color.GRAY);
                mocTextField.setEnabled(true);
                mocJLabel.setForeground(Color.black);

            }
        });

        stacjonarneRadioButton.addActionListener(a -> {
            if (stacjonarneRadioButton.isSelected()) {
                fizycznyNrStanowiskaTextField.setEnabled(true);
                fizycznyNrStanowiskaJLabel.setForeground(Color.BLACK);
                mocTextField.setEnabled(false);
                mocTextField.setText("");
                mocJLabel.setForeground(Color.GRAY);
            }
        });

    }

    private void setDodajNarzedziebuttonActioListenet() {
        dodajNarzedziebutton.addActionListener(a -> {
            String nazwaPotoczna = nazwaPotocznaTextField.getText();
            String pelnaNazwa = pelnaNazwaTextField.getText();
            String dataZakupu = dataZakupuTextField.getText();
            String mocString = mocTextField.getText();
            Integer moc = mocString.equals("") ? null : Integer.valueOf(mocString);
            String fizycznyNumerStanowiskaString = fizycznyNrStanowiskaTextField.getText();
            Integer fizycznyNrStanowiska = fizycznyNumerStanowiskaString.equals("") ?
                    null : Integer.valueOf(fizycznyNumerStanowiskaString);

            // TODO: 17.06.2017 czyszczenie


            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            Date dt = null;
            try {
                dt = sdf.parse(dataZakupu);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            String actionCommand = grupaRadioButton.getSelection().getActionCommand();
            Narzedzie narzedzie = null;
            if (MOBILNE.equals(actionCommand)) {
                narzedzie = Narzedzie.noweMobilne(nazwaPotoczna, pelnaNazwa, dt, moc);
            } else if (STACJONARNE.equals(actionCommand)) {
                narzedzie = Narzedzie.noweStacjonarne(nazwaPotoczna, pelnaNazwa, dt, fizycznyNrStanowiska);
            }
            listaNarzedziModel.addElement(narzedzie);
        });
    }

    private void setListaKlientowINarzedziModeler() throws Exception {
        //lista klientow

        listaKlientowModel.clear();
        if (ObjectPlus.czyJestEkstensjaKlasy(Osoba.class)) {
            Vector<ObjectPlus> klienci = ObjectPlus.dajObiektyZEkstensji(Osoba.class);
            for (ObjectPlus ob : klienci) {
                if (((ObjectPlusPlus) ob).czySaPowiazania(KLIENT_CZESC))
                    listaKlientowModel.addElement((Osoba) ob);
            }
        }
        listaKlientow.setModel(listaKlientowModel);

        //lista narzedzi
        listaNarzedziModel.clear();
        if (ObjectPlus.czyJestEkstensjaKlasy(Narzedzie.class)) {
            Vector<ObjectPlus> narzedzia = ObjectPlus.dajObiektyZEkstensji(Narzedzie.class);
            for (ObjectPlus ob : narzedzia) {
                listaNarzedziModel.addElement((Narzedzie) ob);
            }
        }
        listaNarzedzi.setModel(listaNarzedziModel);

        //lista narzedzi u klienta
        listaNarzedziUKlienta.setModel(listaNarzedziUKlientaModel);


    }

    private void setListaKlientowSelectionListener() {
        listaKlientow.addListSelectionListener(a -> {
            Osoba wybranyKlient = listaKlientow.getSelectedValue();
            String nazwaOsoby = wybranyKlient.toString();
            wybranyKlientKlienciJLabel.setText(nazwaOsoby);
            wybranyKlientNarzedziaJLabel.setText(nazwaOsoby);
            setWypozyczEnabled();

        });
    }

    private void setListaNarzedziSelectionListener(){
        listaNarzedzi.addListSelectionListener(a -> {
            Narzedzie wybraneNarzedzie = listaNarzedzi.getSelectedValue();
            String nazwaNarzedzia = wybraneNarzedzie.toString();
            wybraneNarzedzieNarzedzia.setText(nazwaNarzedzia);


            if(wybraneNarzedzie.czySaPowiazania(KLIENT_POSIADAJACY)){
                try {
                    ObjectPlusPlus[] klient =  wybraneNarzedzie.dajPowiazania(KLIENT_POSIADAJACY);
                    nazwaPosiadajacegoNarzedzie.setText(((Osoba) klient[0]).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else {
                nazwaPosiadajacegoNarzedzie.setText("---");
            }

            setWypozyczEnabled();
        });
    }

    private void setWypozyczEnabled() {
        boolean isEnabled = (!listaNarzedzi.isSelectionEmpty() && !listaKlientow.isSelectionEmpty());
        wypozyczButton.setEnabled(isEnabled);
    }



}
