package de.inmediasp.AddressBook.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ADDRESSBOOK")
public class AddressBook implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	@Column(name = "Name")
	private String Name;

	@Column(name = "Vorname")
	private String Vorname;

	@Column(name = "Str")
	private String Str;

	@Column(name = "Postleitzahl")
	private int Postleitzahl;

	@Column(name = "Stadt")
	private String Stadt;

	@Column(name = "Land")
	private String Land;

	@Column(name = "EMail")
	private String EMail;

	@Column(name = "Telefonnummer")
	private int Telefonnummer;

	@Column(name = "Owner")
	private String Owner;

	public AddressBook() {
	}


	public AddressBook(long id, String name, String vorname, String str, int postleitzahl, String stadt, String land,
					   String eMail, int telefonnummer,  String owner) {
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
		Owner = owner;
	}

	public AddressBook(String name, String vorname, String str, int postleitzahl, String stadt, String land,
					   String eMail, int telefonnummer,  String owner) {
		super();
		Name = name;
		Vorname = vorname;
		Str = str;
		Postleitzahl = postleitzahl;
		Stadt = stadt;
		Land = land;
		EMail = eMail;
		Telefonnummer = telefonnummer;
		Owner = owner;
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


	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

}
