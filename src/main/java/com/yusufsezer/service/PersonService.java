package com.yusufsezer.service;

import com.yusufsezer.entity.Person;
import com.yusufsezer.projection.IPersonList;
import com.yusufsezer.projection.IViewInfo;
import com.yusufsezer.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Iterable<IPersonList> getRandom(int total) {
        List<IPersonList> selectedPerson = new ArrayList<>();
        long count = getCount();

        for (int i = 0; i < total; i++) {
            int rndNumber = (int) (Math.random() * count);
            PageRequest pageRequest = PageRequest.of(rndNumber, 1);
            Page<IPersonList> foundPerson = personRepository.findBy(pageRequest);
            if (foundPerson.hasContent()) {
                selectedPerson.add(foundPerson.getContent().get(0));
            }
        }

        return selectedPerson;
    }

    public Iterable<IPersonList> getLatest() {
        return personRepository.findTop16ByOrderByIdDesc();
    }

    public Iterable<IPersonList> getPopular() {
        return getRandom(10);
    }

    public Optional<Person> findPerson(Long id) {
        return personRepository.findById(id);
    }

    public Page<IPersonList> findByLetter(String letter, Pageable pageable) {
        return personRepository.findByFirstNameStartingWith(letter, pageable);
    }

    public Page<Person> findByTerm(String term, Pageable pageable) {
        Person person = new Person();
        person.setFirstName(term);
        person.setLastName(term);
        person.setDescription(term);

        Example<Person> examplePerson = Example.of(person,
                ExampleMatcher
                        .matchingAny()
                        // org.springframework.dao.InvalidDataAccessResourceUsageException: Parameter 1 of function 'lower()' has type 'STRING', but argument is of type 'java.lang.String'
                        .withIgnoreCase()
                        .withStringMatcher(StringMatcher.CONTAINING)
        );

        return personRepository.findAll(examplePerson, pageable);
    }

    public Iterable<IViewInfo> getPeopleForView() {
        return personRepository.findBy(IViewInfo.class);
    }

    public Page<Person> getPeople(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public long getCount() {
        return personRepository.count();
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public void save(Person person) {
        personRepository.save(person);
    }

}
