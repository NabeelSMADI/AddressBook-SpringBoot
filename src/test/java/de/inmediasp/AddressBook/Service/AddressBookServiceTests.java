//package de.inmediasp.AddressBook.Service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import de.inmediasp.AddressBook.data.entity.AddressBook;
//import de.inmediasp.AddressBook.data.repository.AddressBookRepository;
//
//@SpringBootTest
//class AddressBookServiceTests {
//
//	@Mock
//	private AddressBookRepository addressBookRepository;
//
//	@InjectMocks
//	private AddressBookService addressBookService;
//
//	@BeforeEach
//	void setMockOutput() {
//		AddressBook a1 = new AddressBook("Name1", "vorName1", "str1", 12345, "stadt1", "land1", "eMail1@t.t",
//				123456789);
//		AddressBook a2 = new AddressBook("Name2", "vorName2", "str2", 22345, "stadt2", "land2", "eMail2@t.t",
//				223456789);
//		AddressBook a3 = new AddressBook("Name3", "vorName3", "str3", 32345, "stadt3", "land3", "eMail3@t.t",
//				323456789);
//		AddressBook a4 = new AddressBook("Name4", "vorName4", "str4", 42345, "stadt4", "land4", "eMail4@t.t",
//				423456789);
//		List<AddressBook> addressBookList = Arrays.asList(a1, a2, a3, a4);
//
//		when(addressBookRepository.findAll()).thenReturn(addressBookList);
//		when(addressBookRepository.findById(1L)).thenReturn(Optional.of(a1));
//	}
//
//	@Test
//	public void findAllTest() throws Exception {
//		assertEquals(addressBookService.findAll().size(), 4);
//	}
//
//	@Test
//	public void findbyIdTest() throws Exception {
//		AddressBook a1 = addressBookService.findById(1L);
//		assertEquals(a1.getName(), "Name1");
//	}
//	
//	@Test
//	public void findbyIdTestNotFOund() throws Exception {
//		AddressBook a1 = addressBookService.findById(99L);
//		assertEquals(a1, null);
//	}
//
//	@Test
//	public void addTest() throws Exception {
//		AddressBook a1 = new AddressBook("Name1", "vorName1", "str1", 12345, "stadt1", "land1", "eMail1@t.t",
//				123456789);
//		when(addressBookRepository.save(a1)).thenReturn(a1);
//		AddressBook a2 = addressBookService.add(a1);
//		assertEquals(a2.getName(), "Name1");
//	}
//
//	@Test
//	public void addAllTest() throws Exception {
//		AddressBook a1 = new AddressBook("Name1", "vorName1", "str1", 12345, "stadt1", "land1", "eMail1@t.t",
//				123456789);
//		AddressBook a2 = new AddressBook("Name2", "vorName2", "str2", 22345, "stadt2", "land2", "eMail2@t.t",
//				223456789);
//		AddressBook a3 = new AddressBook("Name3", "vorName3", "str3", 32345, "stadt3", "land3", "eMail3@t.t",
//				323456789);
//
//		List<AddressBook> addressBookList = Arrays.asList(a1, a2, a3);
//		when(addressBookRepository.saveAll(addressBookList)).thenReturn(addressBookList);
//		List<AddressBook> addressBookList2 = addressBookService.addAll(addressBookList);
//		assertEquals(addressBookList2.size(), 3);
//		assertEquals(addressBookList2.get(1).getName(), "Name2");
//	}
//
//	@Test
//	public void updateTest() throws Exception {
//		AddressBook a1 = new AddressBook("Name1", "vorName1", "str1", 12345, "stadt1", "land1", "eMail1@t.t",
//				123456789);
//		when(addressBookRepository.save(a1)).thenReturn(a1);
//		AddressBook a2 = addressBookService.update(a1, 0L);
//		assertEquals(a2.getName(), "Name1");
//	}
//
//	@Test
//	public void deleteTest() throws Exception {
//		when(addressBookRepository.existsById(0L)).thenReturn(true);
//		when(addressBookRepository.findById(0L)).thenReturn(Optional.of(new AddressBook()));
//		addressBookService.delete(0L);
//		verify(addressBookRepository, times(1)).deleteById(0L);
//	}
//	
//	@Test
//	public void deleteAllTest() throws Exception {
//		when(addressBookRepository.existsById(0L)).thenReturn(true);
//		when(addressBookRepository.findById(0L)).thenReturn(Optional.of(new AddressBook()));
//		addressBookService.deleteAll();
//		verify(addressBookRepository, times(1)).deleteAll();
//	}
//	
//	@Test
//	public void existTest() throws Exception {
//		when(addressBookRepository.existsById(0L)).thenReturn(true);
//		assertTrue(addressBookService.exist(0L));
//	}
//	
//	@Test
//	public void isValidEmailAddressTest1() throws Exception {
//		assertTrue(addressBookService.isValidEmailAddress("test@gmail.com"));
//	}
//	
//	@Test
//	public void isValidEmailAddressTest2() throws Exception {
//		assertFalse(addressBookService.isValidEmailAddress("testgmail.com"));
//	}
//	
//	@Test
//	public void filterTest1() throws Exception {
//		AddressBook a1 = new AddressBook("Name1", "vorName1", "str1", 12345, "stadt1", "land1", "eMail1@t.t",
//				123456789);
//		List<AddressBook> addressBookList = Arrays.asList(a1);
//		when(addressBookRepository.CustomFilter(" Name='Name1'")).thenReturn(addressBookList);
//		List<AddressBook> addressBookList2 = addressBookService.filter("Name1", null, null, null, null, null, null, null);
//		assertEquals(addressBookList2.size(), 1);
//		assertEquals(addressBookList2.get(0).getName(), "Name1");
//	}
//	
//	@Test
//	public void filterTest2() throws Exception {
//		AddressBook a1 = new AddressBook("Name1", "vorName1", "str1", 12345, "stadt1", "land1", "eMail1@t.t",
//				123456789);
//		AddressBook a2 = new AddressBook("Name2", "vorName2", "str2", 22345, "stadt1", "land1", "eMail2@t.t",
//				223456789);
//		List<AddressBook> addressBookList = Arrays.asList(a1,a2);
//		when(addressBookRepository.CustomFilter(" Stadt='stadt1' and  Land='land1'")).thenReturn(addressBookList);
//		List<AddressBook> addressBookList2 = addressBookService.filter(null, null, null, null, "stadt1", "land1", null, null);
//		assertEquals(addressBookList2.size(), 2);
//		assertEquals(addressBookList2.get(0).getStadt(), "stadt1");
//		assertEquals(addressBookList2.get(1).getLand(), "land1");
//	}
//
//}
