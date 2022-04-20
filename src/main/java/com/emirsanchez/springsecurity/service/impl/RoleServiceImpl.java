package com.emirsanchez.springsecurity.service.impl;

import com.emirsanchez.springsecurity.domain.Role;
import com.emirsanchez.springsecurity.repository.IRoleRepository;
import com.emirsanchez.springsecurity.service.IRoleService;
import com.emirsanchez.springsecurity.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Log4j
@Transactional
@Service
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository repo;

    @Override
    public Role saveRole(Role role) {
        return repo.save(role);
    }
}
