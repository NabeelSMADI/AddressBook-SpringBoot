package de.inmediasp.AddressBook.data.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import de.inmediasp.AddressBook.data.entity.AddressBook;
/**
* 
*  This interface is the AddressBook Spring data Repository
*
* @author  Nabeel Smadi
* @version 1.0
* @since   2020-01-07
*/
@Repository
public interface AddressBookRepository extends MongoRepository<AddressBook, Long>, AddressBookRepositoryCustomFilter {

	

}

