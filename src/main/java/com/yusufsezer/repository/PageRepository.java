package com.yusufsezer.repository;

import com.yusufsezer.entity.Page;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {

    Optional<Page> findBySlug(String slug);

    <T> Optional<T> getById(Long id, Class<T> type);

}
