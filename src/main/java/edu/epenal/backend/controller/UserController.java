package edu.epenal.backend.controller;

import edu.epenal.backend.model.dto.UserDTO;
import edu.epenal.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;

    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/user")
    public ResponseEntity<UserDTO> upsertUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(userService.upsertUser(user));

    }
}
