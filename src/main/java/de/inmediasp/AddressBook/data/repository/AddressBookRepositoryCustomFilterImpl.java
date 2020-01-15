package de.inmediasp.AddressBook.data.repository;

import java.util.List;



import de.inmediasp.AddressBook.data.entity.AddressBook;

/**
* 
*  This class is the AddressBook Custom Repository Implementation 
*
* @author  Nabeel Smadi
* @version 1.0
* @since   2020-01-07
*/
public class AddressBookRepositoryCustomFilterImpl implements AddressBookRepositoryCustomFilter  {

//	@PersistenceContext
//	private EntityManager em;

	/**
	 * This method is used to get all the Address Books from the Database with the selected Filters
	 * 
	 * @return <List<AddressBook>> this return an AddressBooks list of the results
	 */
	@Override
	public List<AddressBook> CustomFilter(String filter) {
//		Query q = em.createQuery("select a from AddressBook a where " + filter);
		return (List<AddressBook>) null;

	}

}
