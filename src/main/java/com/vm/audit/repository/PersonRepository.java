/**
 * Methods for accessing the database
 */
package com.vm.audit.repository;

import com.vm.audit.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Victor Munna
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * Finds the person by name or last name
     *
     * @param id
     * @param name
     * @param lastName
     * @return
     */
    Person findByIdOrFirstNameOrLastName(int id, String name, String lastName);

}
