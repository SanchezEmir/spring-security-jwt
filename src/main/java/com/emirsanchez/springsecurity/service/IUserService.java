package com.emirsanchez.springsecurity.service;

import com.emirsanchez.springsecurity.domain.Role;
import com.emirsanchez.springsecurity.domain.User;

import java.util.List;

public interface IUserService {

    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    User saveUser(User user);
    Role saveRole(Role role);


}
