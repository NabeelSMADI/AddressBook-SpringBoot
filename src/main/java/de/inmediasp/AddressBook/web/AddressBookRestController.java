package de.inmediasp.AddressBook.web;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import de.inmediasp.AddressBook.Service.AddressBookService;
import de.inmediasp.AddressBook.data.entity.AddressBook;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;


/**
* 
*  This class is the Rest API Controller for the AddressBooks
*
* @author  Nabeel Smadi
* @version 1.0
* @since   2020-01-07
*/
@RestController
@CrossOrigin
@RequestMapping("api/AddressBook")
public class AddressBookRestController {
	
	private static final Logger log = LoggerFactory.getLogger(AddressBookRestController.class);

	private final AddressBookService addressBookService;

	@Autowired
	public AddressBookRestController(AddressBookService addressBookService) {
		super();
		this.addressBookService = addressBookService;
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
	@GetMapping
	ResponseEntity<List<AddressBook>> findAllWithFilter(@RequestParam(required = false) String name,
			@RequestParam(required = false) String vorname, @RequestParam(required = false) String str,
			@RequestParam(required = false) Integer postleitzahl, @RequestParam(required = false) String stadt,
			@RequestParam(required = false) String land, @RequestParam(required = false) String email,
			@RequestParam(required = false) Integer telefonnummer) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(addressBookService.filter(name, vorname, str, postleitzahl, stadt, land, email, telefonnummer));
	}

	/**
	 * This method is used to get an Address Book by its id
	 * 
	 * @param id This is the AddressBook id 
	 * @return AddressBook this return an AddressBook of the requested id
	 */
	@GetMapping("/{id}")
	@PostAuthorize("hasPermission(returnObject,'Read')")
	ResponseEntity<AddressBook> getAddressBookbyId(@PathVariable Long id) {
		if (addressBookService.exist(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(addressBookService.findById(id));
		} else {
			log.info("-------------------------------");
			log.info("AddressBook with id:" + id + " Not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	/**
	 * This method is used to add a new Address Book to the Database
	 * 
	 * @param newAddressBook This is the new AddressBook  
	 * @return ResponseEntity this return the new AddressBook and its id in the location Header
	 */
	@PostMapping
	ResponseEntity addAddressBook(@RequestBody AddressBook newAddressBook, Authentication authentication) {
		if (newAddressBook == null) {
			log.info("-------------------------------");
			log.info("AddressBook object is null");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Data");
		}
		if (addressBookService.exist(newAddressBook.getId())) {
			log.info("-------------------------------");
			log.info("AddressBook with id:" + newAddressBook.getId() + " is already exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("The AddressBook with Id " + newAddressBook.getId() + " already exists");
		}
		if (newAddressBook.getName() == null || newAddressBook.getVorname() == null
				|| newAddressBook.getEMail() == null) {
			log.info("-------------------------------");
			log.info("AddressBook Missing Data");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Data");
		}
		if (newAddressBook.getName().equals("") || newAddressBook.getVorname().equals("")
				|| newAddressBook.getEMail().equals("")) {
			log.info("-------------------------------");
			log.info("AddressBook Missing Data");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Data");
		}
		if (!addressBookService.isValidEmailAddress(newAddressBook.getEMail())) {
			log.info("-------------------------------");
			log.info("AddressBook Email Address is Not Valid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Valid Email Address");
		}
		newAddressBook.setOwner(authentication.getName());
		newAddressBook = addressBookService.add(newAddressBook);
		log.info("-------------------------------");
		log.info("AddressBook with the id:" + newAddressBook.getId() + " is added");
		return ResponseEntity.status(HttpStatus.CREATED)
				.header("location", "/api/AddressBook/" + newAddressBook.getId())
				.body(newAddressBook);
	}

	/**
	 * This method is used to add new Multi Address Books to the Database
	 * 
	 * @param newAddressBooks This is the new AddressBooks in a list  
	 * @return ResponseEntity this return the new added AddressBooks
	 */
	@PostMapping("/addMulti")
	ResponseEntity addMultiAddressBooks(@RequestBody List<AddressBook> newAddressBooks, Authentication authentication) {
		if (newAddressBooks == null) {
			log.info("-------------------------------");
			log.info("AddressBook list object is null");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Data");
		}
		if (newAddressBooks.isEmpty()) {
			log.info("-------------------------------");
			log.info("AddressBook list is Empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("AddressBook list is Empty");
		}
		for (AddressBook newAddressBook : newAddressBooks) {
			if (newAddressBook == null) {
				log.info("-------------------------------");
				log.info("AddressBook object is null");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Data");
			}
			if (addressBookService.exist(newAddressBook.getId())) {
				log.info("-------------------------------");
				log.info("AddressBook with id:" + newAddressBook.getId() + " is already exist");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("The AddressBook with Id " + newAddressBook.getId() + " already exists");
			}
			if (newAddressBook.getName() == null || newAddressBook.getVorname() == null
					|| newAddressBook.getEMail() == null) {
				log.info("-------------------------------");
				log.info("AddressBook Missing Data");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Data");
			}
			if (newAddressBook.getName().equals("") || newAddressBook.getVorname().equals("")
					|| newAddressBook.getEMail().equals("")) {
				log.info("-------------------------------");
				log.info("AddressBook Missing Data");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Data");
			}
			if (!addressBookService.isValidEmailAddress(newAddressBook.getEMail())) {
				log.info("-------------------------------");
				log.info("AddressBook Email Address is Not Valid");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Valid Email Address");
			}
			newAddressBook.setOwner(authentication.getName());
		}
		newAddressBooks = addressBookService.addAll(newAddressBooks);
		log.info("-------------------------------");
		log.info("AddressBooks are added");
		return ResponseEntity.status(HttpStatus.CREATED).body(newAddressBooks);
	}

	/**
	 * This method is used to update an AddressBook information in the Database
	 * 
	 * @param newAddressBooks This is the new AddressBooks in a list  
	 * @return ResponseEntity this return the updated AddressBook
	 */
	@PutMapping("/{id}")
	ResponseEntity updateAddressBook(@RequestBody AddressBook addressBook, @PathVariable Long id) {
		if (addressBookService.exist(id)) {
			if (addressBook.getName() == null || addressBook.getVorname() == null
					|| addressBook.getEMail() == null) {
				log.info("-------------------------------");
				log.info("AddressBook Missing Data");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Data");
			}
			if (addressBook.getName().equals("") || addressBook.getVorname().equals("")
					|| addressBook.getEMail().equals("")) {
				log.info("-------------------------------");
				log.info("AddressBook Missing Data");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Data");
			}
			if (!addressBookService.isValidEmailAddress(addressBook.getEMail())) {
				log.info("-------------------------------");
				log.info("Not Valid Email Address");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Valid Email Address");
			}
			log.info("-------------------------------");
			log.info("AddressBook with the id:" + id + "is updated");
			return ResponseEntity.status(HttpStatus.OK).body(addressBookService.update(addressBook, id));
		} else {
			log.info("-------------------------------");
			log.info("AddressBook with id:" + id + " Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	/**
	 * This method is used to delete an AddressBook from the database by its id
	 * 
	 * @param id This is the AddressBook id
	 */
	@DeleteMapping("/{id}")
	ResponseEntity deleteAddressBook(@PathVariable Long id) {
		if (addressBookService.exist(id)) {
			addressBookService.delete(id);
			log.info("-------------------------------");
			log.info("AddressBook with the id:" + id + "is deleted");
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			log.info("-------------------------------");
			log.info("AddressBook with id:" + id + " Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	/**
	 * This method is used to delete all AddressBooks from the database
	 */
	@DeleteMapping
	@PreAuthorize("hasPermission(null,'deleteAll')")
	ResponseEntity deleteAllAddressBooks() {
		addressBookService.deleteAll();
		log.info("-------------------------------");
		log.info("All AddressBooks are deleted");
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
