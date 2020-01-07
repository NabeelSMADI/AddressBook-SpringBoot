package de.inmediasp.AddressBook.data.repository;

import java.util.List;

import de.inmediasp.AddressBook.data.entity.AddressBook;

/**
* 
*  This interface is the AddressBook Custom Repository
*
* @author  Nabeel Smadi
* @version 1.0
* @since   2020-01-07
*/
public interface AddressBookRepositoryCustomFilter {

	public List<AddressBook> CustomFilter(String filter);
	
}
