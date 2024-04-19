package com.yusufsezer.service;

import com.yusufsezer.dto.MemberDTO;
import com.yusufsezer.dto.RegisterDTO;
import com.yusufsezer.entity.Author;
import com.yusufsezer.enumeration.Role;
import com.yusufsezer.exception.AuthorAlreadyExistException;
import com.yusufsezer.projection.IMember;
import com.yusufsezer.repository.AuthorRepository;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthorService(AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Iterable<IMember> getMembers() {
        return authorRepository.findBy(Sort.by(Sort.Order.desc("id")));
    }

    public Optional<Author> getMember(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<MemberDTO> findById(Long id) {
        return authorRepository.getById(id, MemberDTO.class);
    }

    public Optional<Author> findByUsername(String username) {
        return authorRepository.findByEmail(username);
    }

    public boolean check(String email) {
        return authorRepository.existsByEmail(email);
    }

    public void approve(Author author) {
        author.setActive(true);
        save(author);
    }

    public void register(RegisterDTO registerDTO)
            throws AuthorAlreadyExistException {

        String email = registerDTO.email();
        if (check(email)) {
            throw new AuthorAlreadyExistException();
        }

        Author newAuthor = registerDTO.toAuthor();
        String encodedPassword = passwordEncoder.encode(registerDTO.password());
        newAuthor.setPassword(encodedPassword);
        newAuthor.setRole(Role.WRITER);
        newAuthor.setActive(false);
        save(newAuthor);
    }

    public long count() {
        return authorRepository.count();
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

}
