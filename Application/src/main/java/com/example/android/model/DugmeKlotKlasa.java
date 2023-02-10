package com.example.android.model;

public class DugmeKlotKlasa {
	private static int brojac = 0;
	private String pise, salje;

	public DugmeKlotKlasa() {
		this.pise = "pise " + brojac;
		this.salje = "salje " + brojac;
		brojac++;
	}

	public String getPise() {
		return pise;
	}

	public void setPise(String pise) {
		this.pise = pise;
	}

	public String getSalje() {
		return salje;
	}

	public void setSalje(String salje) {
		this.salje = salje;
	}
}
