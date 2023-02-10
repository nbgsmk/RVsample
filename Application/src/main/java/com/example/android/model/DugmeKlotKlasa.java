package com.example.android.model;

public class DugmeKlotKlasa {
    private static int brojac = 0;
    private String ime, prezime;

    public DugmeKlotKlasa() {
        this.ime = "ime " + brojac;
        this.prezime = "prezime " + brojac;
        brojac++;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
