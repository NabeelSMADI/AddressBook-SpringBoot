package de.inmediasp.AddressBook.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.inmediasp.AddressBook.data.entity.AddressBook;
import de.inmediasp.AddressBook.data.repository.AddressBookRepository;

/**
* 
*  This class is the AddressBook Service in Spring
*
* @author  Nabeel Smadi
* @version 1.0
* @since   2020-01-07
*/
@Service
public class AddressBookService {

	private static final Logger log = LoggerFactory.getLogger(AddressBookService.class);

	private final AddressBookRepository addressBookRepository;

	@Autowired
	public AddressBookService(AddressBookRepository addressBookRepository) {
		this.addressBookRepository = addressBookRepository;
	}

	/**
	 * This method is used to get all the Address Books
	 * 
	 * @return <List<AddressBook>> this return an AddressBooks list of the results
	 */
	public List<AddressBook> findAll() {
		log.info("-------------------------------");
		log.info("get all AddressBooks");
		return addressBookRepository.findAll();
	}

	/**
	 * This method is used to get an Address Book by its id
	 * 
	 * @param id This is the AddressBook id 
	 * @return AddressBook this return an AddressBook of the requested id
	 */
	public AddressBook findById(Long id) {
		log.info("-------------------------------");
		log.info("get an AddressBook with id:" + id);
		return addressBookRepository.findById(id).orElse(null);
	}

	/**
	 * This method is used to add a new Address Book to the Database
	 * 
	 * @param newAddressBook This is the new AddressBook  
	 * @return AddressBook this return the new AddressBook and its id in the location Header
	 */
	public AddressBook add(AddressBook newAddressBook) {
		log.info("-------------------------------");
		log.info("add a new AddressBook");
		return addressBookRepository.save(newAddressBook);
	}

	/**
	 * This method is used to add new Multi Address Books to the Database
	 * 
	 * @param newAddressBooks This is the new AddressBooks in a list  
	 * @return List<AddressBook> this return the new added AddressBooks
	 */
	public List<AddressBook> addAll(List<AddressBook> newAddressBooks) {
		log.info("-------------------------------");
		log.info("add new Multi AddressBooks");
		return addressBookRepository.saveAll(newAddressBooks);
	}
	
	/**
	 * This method is used to update an AddressBook information in the Database
	 * 
	 * @param newAddressBooks This is the new AddressBooks in a list  
	 * @return AddressBook this return the updated AddressBook
	 */
	public AddressBook update(AddressBook newAddressBook, Long id) {
		newAddressBook.setId(id);
		log.info("-------------------------------");
		log.info("update AddressBook with the id:" + id);
		return addressBookRepository.save(newAddressBook);
	}

	/**
	 * This method is used to delete an AddressBook from the database by its id
	 * 
	 * @param id This is the AddressBook id
	 */
	public boolean delete(Long id) {
		if (exist(id)) {
			addressBookRepository.deleteById(id);
			log.info("-------------------------------");
			log.info("delete AddressBook with the id:" + id);
			return true;
		} else {
			log.info("-------------------------------");
			log.info("AddressBook with the id:" + id + " Not Found");
			return false;
		}
	}

	/**
	 * This method is used to delete all AddressBooks from the database
	 */
	public void deleteAll() {
		addressBookRepository.deleteAll();
		log.info("-------------------------------");
		log.info("delete all AddressBooks from the database");
	}

	/**
	 * This method is used to check if an AddressBook exist in the database
	 */
	public boolean exist(Long id) {
		return addressBookRepository.existsById(id);
	}

	/**
	 * This method is used to check if an EMail format is Valid
	 */
	public boolean isValidEmailAddress(String email) {
		EmailValidator emailValidator = new EmailValidator();
		return emailValidator.isValid(email, null);
	}

	/**
	 * This method is used to get all the Address Books with the ability to set different filters
	 * 
	 * @param name This is the AddressBook Name Field
	 * @param vorname This is the AddressBook Vorname Field
	 * @param str This is the AddressBook str Field
	 * @param postleitzahl This is the AddressBook postleitzahl Field
	 * @param stadt This is the AddressBook stadt Field
	 * @param land This is the AddressBook land Field
	 * @param email This is the AddressBook email Field
	 * @param telefonnummer This is the AddressBook telefonnummer Field
	 * @return <List<AddressBook>> this return a AddressBooks list of the results
	 */
	public List<AddressBook> filter(String name, String vorname, String str, Integer postleitzahl, String stadt,
			String land, String email, Integer telefonnummer) {

		if (name == null && vorname == null && str == null && postleitzahl == null && stadt == null && land == null
				&& email == null && telefonnummer == null) {
			return findAll();
		} else {
			String search = "";
			if (name != null)
				search = " Name='" + name + "'";

			if (!search.equals("") && vorname != null)
				search = search + " and ";
			if (vorname != null)
				search = search + " Vorname='" + vorname + "'";

			if (!search.equals("") && str != null)
				search = search + " and ";
			if (str != null)
				search = search + " Str='" + str + "'";

			if (!search.equals("") && postleitzahl != null)
				search = search + " and ";
			if (postleitzahl != null)
				search = search + " Postleitzahl='" + postleitzahl + "'";

			if (!search.equals("") && stadt != null)
				search = search + " and ";
			if (stadt != null)
				search = search + " Stadt='" + stadt + "'";

			if (!search.equals("") && land != null)
				search = search + " and ";
			if (land != null)
				search = search + " Land='" + land + "'";

			if (!search.equals("") && email != null)
				search = search + " and ";
			if (email != null)
				search = search + " Email='" + email + "'";

			if (!search.equals("") && telefonnummer != null)
				search = search + " and ";
			if (telefonnummer != null)
				search = search + " Telefonnummer='" + telefonnummer + "'";
			
			log.info("-------------------------------");
			log.info("Find all AddressBooks with Filter:" + search);
			
			List<AddressBook> result = addressBookRepository.CustomFilter(search);
			return result;
		}
	}

}
