/**
 * Implementation of the method signatures
 */
package com.vm.audit.service;

import com.googlecode.jmapper.JMapper;
import com.vm.audit.domain.Person;
import com.vm.audit.dto.PersonDTO;
import com.vm.audit.repository.PersonRepository;
import com.vm.audit.util.Utilities;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Victor Munna
 */
@Service
@Transactional
@CommonsLog
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private String response;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findByText(String id, String name, String lastName) {
        Integer personId = Utilities.isInteger(id);
        return personRepository.findByIdOrFirstNameOrLastName(personId, name, lastName);
    }

    @Override
    public Person findById(int id) {
        return personRepository.findOne(id);
    }

    @Override
    public String save(PersonDTO personDTO) {
        response = "Person saved!";
        JMapper<Person, PersonDTO> mapper = new JMapper<>(Person.class, PersonDTO.class);
        Person person = mapper.getDestination(personDTO);
        log.info("JMapper: " + person.toString());
        personRepository.save(person);
        log.info(response);
        return response;
    }

    @Override
    public String update(PersonDTO personDTO) {
        response = "Person updated!";
        Person person = personRepository.findOne(personDTO.getId());
        person = this.updatePerson(person, personDTO);
        personRepository.save(person);
        log.info(response);
        return response;
    }

    @Override
    public String deleteById(int id) {
        response = "Person deleted!";
        personRepository.delete(id);
        log.info(response);
        return response;
    }

    private Person updatePerson(Person person, PersonDTO personDTO) {
        person.setFirstName(personDTO.getName());
        person.setLastName(personDTO.getLastName());
        return person;
    }

}
