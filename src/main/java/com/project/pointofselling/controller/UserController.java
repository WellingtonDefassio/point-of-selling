package com.project.pointofselling.controller;

import com.project.pointofselling.entity.User;
import com.project.pointofselling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity getAll() {
        return new ResponseEntity(this.userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody User user) {
        try {
            return new ResponseEntity(userRepository.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity put(@RequestBody User user) {
        Optional<User> userById = userRepository.findById(user.getId());
        if(userById.isPresent()){
            User save = this.userRepository.save(user);
            return new ResponseEntity(save, HttpStatus.ACCEPTED);
        }
        return ResponseEntity.notFound().build();
    }
}
