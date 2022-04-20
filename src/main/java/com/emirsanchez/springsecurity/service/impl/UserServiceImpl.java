package com.emirsanchez.springsecurity.service.impl;

import com.emirsanchez.springsecurity.domain.Role;
import com.emirsanchez.springsecurity.domain.User;
import com.emirsanchez.springsecurity.repository.IRoleRepository;
import com.emirsanchez.springsecurity.repository.IUserRepository;
import com.emirsanchez.springsecurity.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Log4j
@Transactional
@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository repoUser;
    private final IRoleRepository repoRole;

    @Override
    public void addRoleToUser(String username, String roleName) {

        User user = repoUser.findByUserName(username);
        Role role = repoRole.findByName(username);

        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        return repoUser.findByUserName(username);
    }

    @Override
    public List<User> getUsers() {
        return repoUser.findAll();
    }

    @Override
    public User saveUser(User user) {
        return repoUser.save(user);
    }


}
