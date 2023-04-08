package com.algebra.restapialgebra.controller;

import com.algebra.restapialgebra.model.User;
import com.algebra.restapialgebra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    public UsersController(UserRepository userRepository) { this.userRepository = userRepository;}

    @GetMapping("")
    List<User> index() { return userRepository.findAll();}

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id){ return userRepository.findById(id).orElseThrow(RuntimeException::new);}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    User create(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("{id}")
    User update(@PathVariable Long id, @RequestBody User user){
        User userFromBD = userRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        userFromBD.setName(user.getName());
        userFromBD.setEmail(user.getEmail());
        userFromBD.setPassword(user.getPassword());

        return userRepository.save(userFromBD);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable Long id){
        User user = userRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        userRepository.delete(user);
    }

}
