package com.emirsanchez.springsecurity.repository;

import com.emirsanchez.springsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository  extends JpaRepository<User, Long> {

    User findByUserName(String username);

}
