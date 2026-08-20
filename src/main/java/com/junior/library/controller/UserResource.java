package com.junior.library.controller;

import com.junior.library.entitie.User;
import com.junior.library.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> all = userService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User byId = userService.findById(id);
        return ResponseEntity.ok().body(byId);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User saved = userService.save(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<User>> saveAll(@RequestBody List<User> userList) {
        List<User> users = userService.saveAll(userList);
        return ResponseEntity.ok(users);
    }
}
