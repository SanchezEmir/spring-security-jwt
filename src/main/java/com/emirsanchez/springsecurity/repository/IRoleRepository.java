package com.emirsanchez.springsecurity.repository;

import com.emirsanchez.springsecurity.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
