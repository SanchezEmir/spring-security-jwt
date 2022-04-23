package com.emirsanchez.springsecurity.service.impl;

import com.emirsanchez.springsecurity.domain.Role;
import com.emirsanchez.springsecurity.domain.User;
import com.emirsanchez.springsecurity.repository.IRoleRepository;
import com.emirsanchez.springsecurity.repository.IUserRepository;
import com.emirsanchez.springsecurity.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final IUserRepository repoUser;
    private final IRoleRepository repoRole;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repoUser.findByUsername(username);
        if (user == null) {

            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");

        } else {

            log.info("User found in the database: {}", username);

        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add((new SimpleGrantedAuthority(role.getName())));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = repoUser.findByUsername(username);
        Role role = repoRole.findByName(roleName);

        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        log.info("Fecching user {}", username);
        return repoUser.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fecching all users");
        return repoUser.findAll();
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new User {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repoUser.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new {} User to the database", role.getName());
        return repoRole.save(role);
    }


}
