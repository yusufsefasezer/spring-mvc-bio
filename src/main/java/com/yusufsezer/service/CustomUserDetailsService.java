package com.yusufsezer.service;

import com.yusufsezer.entity.Author;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthorService authorService;

    public CustomUserDetailsService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorService
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(""));
        return new CustomUserDetails(author);
    }

    public class CustomUserDetails implements UserDetails {

        Author author;

        public CustomUserDetails(Author author) {
            this.author = author;
        }

        public Author getAuthor() {
            return author;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList(author.getRole().name());
        }

        @Override
        public String getPassword() {
            return author.getPassword();
        }

        @Override
        public String getUsername() {
            return author.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return author.isActive();
        }

    }

}
