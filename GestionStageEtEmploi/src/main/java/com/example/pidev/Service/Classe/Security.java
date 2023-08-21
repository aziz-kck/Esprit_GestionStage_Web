package com.example.pidev.Service.Classe;


import com.example.pidev.DAO.Entities.User;
import com.example.pidev.DAO.Repositories.RoleRepositories;
import com.example.pidev.DAO.Repositories.UserRepositories;
import com.example.pidev.Service.Interface.ISecurity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class Security implements ISecurity, UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepositories userRepo;
    @Autowired
    RoleRepositories rolesRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepo.findByMail(email);

        if (user == null) {
            log.error("user not found in the database");
        } else {
            log.info("user found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoles().getName()));
        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), authorities);

    }

    @Override
    public User getUser(String email) {
        return userRepo.findByMail(email);   }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }
    @Override
    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }


}