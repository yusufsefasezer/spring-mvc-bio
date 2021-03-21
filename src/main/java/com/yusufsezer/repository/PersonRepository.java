package com.yusufsezer.repository;

import com.yusufsezer.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yusufsezer.projection.IPersonList;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Iterable<IPersonList> findTop16ByOrderByIdDesc();

    Page<IPersonList> findByFirstNameStartingWith(String firstName, Pageable pageable);

    <T> Iterable<T> findBy(Class<T> type);

    Page<IPersonList> findBy(Pageable pageable);

}
