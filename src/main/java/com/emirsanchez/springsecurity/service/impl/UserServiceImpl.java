package com.emirsanchez.springsecurity.service.impl;

import com.emirsanchez.springsecurity.domain.Role;
import com.emirsanchez.springsecurity.domain.User;
import com.emirsanchez.springsecurity.repository.IRoleRepository;
import com.emirsanchez.springsecurity.repository.IUserRepository;
import com.emirsanchez.springsecurity.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository repoUser;
    private final IRoleRepository repoRole;

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
        return repoUser.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new {} User to the database", role.getName());
        return repoRole.save(role);
    }


}
