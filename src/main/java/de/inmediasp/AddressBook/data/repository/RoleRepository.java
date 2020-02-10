package de.inmediasp.AddressBook.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.AddressBook.data.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);
}
