/**
 * Implementation of the method signatures
 */
package com.vm.jms.service;

import com.googlecode.jmapper.JMapper;
import com.vm.jms.domain.Person;
import com.vm.jms.dto.PersonDTO;
import com.vm.jms.repository.PersonRepository;
import com.vm.jms.util.Utilities;
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
        response = "PersonAudit saved!";
        JMapper<Person, PersonDTO> mapper = new JMapper<>(Person.class, PersonDTO.class);
        Person person = mapper.getDestination(personDTO);
        log.info("JMapper: " + person.toString());
        personRepository.save(person);
        log.info(response);
        return response;
    }

    @Override
    public String update(PersonDTO personDTO) {
        response = "PersonAudit updated!";
        Person person = personRepository.findOne(personDTO.getId());
        person = this.updatePerson(person, personDTO);
        personRepository.save(person);
        log.info(response);
        return response;
    }

    @Override
    public String deleteById(int id) {
        response = "PersonAudit deleted!";
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
