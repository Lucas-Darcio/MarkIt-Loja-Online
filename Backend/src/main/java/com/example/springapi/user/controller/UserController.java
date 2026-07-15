package com.example.springapi.user.controller;

import com.example.springapi.user.model.User;
import com.example.springapi.user.service.UserService;
import com.example.springapi.user.dto.UserRequestDTO;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /user
    @GetMapping
    public ResponseEntity<List<User>> index() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /user/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // POST /user
    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserRequestDTO dto) {
        User newUser = userService.createUser(dto);
        return ResponseEntity.ok(newUser);
    }

    // PUT /user/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody @Valid UserRequestDTO dto, @PathVariable UUID id){
        User editedUser = userService.updateUser(id, dto);
        return ResponseEntity.ok(editedUser);
    }

    // DELETE /user/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }

}
