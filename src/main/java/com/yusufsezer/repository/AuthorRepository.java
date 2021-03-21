package com.yusufsezer.repository;

import com.yusufsezer.dto.MemberDTO;
import com.yusufsezer.entity.Author;
import com.yusufsezer.projection.IMember;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<MemberDTO> getById(Long id);

    Iterable<IMember> findBy(Sort sort);

}
