package de.inmediasp.AddressBook.data.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AddressBook")
public class AddressBook implements Serializable {

	@Id
	private long Id;

	private String Name;

	private String Vorname;

	private String Str;

	private int Postleitzahl;

	private String Stadt;

	private String Land;

	private String EMail;

	private int Telefonnummer;

	public AddressBook() {
	}

	public AddressBook(long id, String name, String vorname, String str, int postleitzahl, String stadt, String land,
			String eMail, int telefonnummer) {
		super();
		Id = id;
		Name = name;
		Vorname = vorname;
		Str = str;
		Postleitzahl = postleitzahl;
		Stadt = stadt;
		Land = land;
		EMail = eMail;
		Telefonnummer = telefonnummer;
	}
	
	public AddressBook( String name, String vorname, String str, int postleitzahl, String stadt, String land,
			String eMail, int telefonnummer) {
		super();
		Name = name;
		Vorname = vorname;
		Str = str;
		Postleitzahl = postleitzahl;
		Stadt = stadt;
		Land = land;
		EMail = eMail;
		Telefonnummer = telefonnummer;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getVorname() {
		return Vorname;
	}

	public void setVorname(String vorname) {
		Vorname = vorname;
	}

	public String getStr() {
		return Str;
	}

	public void setStr(String str) {
		Str = str;
	}

	public int getPostleitzahl() {
		return Postleitzahl;
	}

	public void setPostleitzahl(int postleitzahl) {
		Postleitzahl = postleitzahl;
	}

	public String getStadt() {
		return Stadt;
	}

	public void setStadt(String stadt) {
		Stadt = stadt;
	}

	public String getLand() {
		return Land;
	}

	public void setLand(String land) {
		Land = land;
	}

	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public int getTelefonnummer() {
		return Telefonnummer;
	}

	public void setTelefonnummer(int telefonnummer) {
		Telefonnummer = telefonnummer;
	}

	

}
