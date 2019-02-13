package com.sc.studentcorner.service;

import com.sc.studentcorner.model.Admin;
import com.sc.studentcorner.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;

@Service
public class AdminService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);


    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Admin saveAdmin(String email, String password, String name, String surname, String faculty) {
        boolean adminExist = repository.existsByEmail(email);
        if (adminExist) {
            logger.warn("User [{}] already exists", email);
            throw new EntityExistsException(email);
        } else {
            Admin newAdmin = new Admin(email, name, surname, faculty);
            newAdmin.setPassword(passwordEncoder.encode(password));
            return repository.save(newAdmin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading owner: [{}]", username);
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

