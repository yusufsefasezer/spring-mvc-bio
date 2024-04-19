package com.yusufsezer.repository;

import com.yusufsezer.entity.Author;
import com.yusufsezer.projection.IMember;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);

    boolean existsByEmail(String email);

    <T> Optional<T> getById(Long id, Class<T> type);

    Iterable<IMember> findBy(Sort sort);

}
