package de.inmediasp.AddressBook.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.AddressBook.data.entity.User;

import java.util.Optional;

/**
 * Created by Mary Ellen Bowman.
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String userName);
}