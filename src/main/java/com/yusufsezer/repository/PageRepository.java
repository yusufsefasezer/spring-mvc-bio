package com.yusufsezer.repository;

import com.yusufsezer.dto.PageDTO;
import com.yusufsezer.entity.Page;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {

    Optional<Page> findBySlug(String slug);

    Optional<PageDTO> getById(Long id);

}
