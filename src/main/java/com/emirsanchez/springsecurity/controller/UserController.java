package com.emirsanchez.springsecurity.controller;

import com.emirsanchez.springsecurity.domain.Role;
import com.emirsanchez.springsecurity.domain.User;
import com.emirsanchez.springsecurity.service.IUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final IUserService userService;

    @GetMapping("/user/list")
    public ResponseEntity<List<User>> getUsers() {

        return ResponseEntity.ok().body(userService.getUsers());

    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveUser(user));

    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(role));

    }

    @PostMapping("/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleUserForm form) {

        userService.addRoleToUser(form.getUsername(), form.getRoleName());

        return ResponseEntity.ok().build();

    }


}

@Getter
@Setter
class RoleUserForm {

    private String username;
    private String roleName;

}
