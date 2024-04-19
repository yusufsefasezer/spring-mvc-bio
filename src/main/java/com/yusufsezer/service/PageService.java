package com.yusufsezer.service;

import com.yusufsezer.dto.PageDTO;
import com.yusufsezer.entity.Page;
import com.yusufsezer.repository.PageRepository;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private final PageRepository pageRepository;

    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public Optional<Page> findBySlug(String slug) {
        return pageRepository.findBySlug(slug);
    }

    public Optional<Page> findById(Long id) {
        return pageRepository.findById(id);
    }

    public Optional<PageDTO> findPage(Long id) {
        return pageRepository.getById(id, PageDTO.class);
    }

    public Iterable<Page> getPages() {
        return pageRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public long getCount() {
        return pageRepository.count();
    }

    public void deletePage(Long id) {
        pageRepository.deleteById(id);
    }

    public void save(Page page) {
        pageRepository.save(page);
    }

}
