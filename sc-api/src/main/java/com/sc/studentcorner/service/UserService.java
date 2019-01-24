package com.sc.studentcorner.service;

import com.sc.studentcorner.model.User;
import com.sc.studentcorner.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserService  implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(String email, String password) {
        boolean userExists = repository.existsByEmail(email);
        if (userExists) {
            logger.warn("User [{}] already exists", email);
            throw new EntityExistsException(email);
        } else {
            User newUser = new User(email);
            newUser.setPassword(passwordEncoder.encode(password));
            return repository.save(newUser);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user: [{}]", username);
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
