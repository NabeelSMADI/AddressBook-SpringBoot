package de.inmediasp.AddressBook.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADDRESSBOOK")
@Getter @Setter @NoArgsConstructor 
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

	
}
