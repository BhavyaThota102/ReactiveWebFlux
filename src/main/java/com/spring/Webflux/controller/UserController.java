package com.spring.Webflux.controller;
import com.spring.Webflux.entities.User;
import com.spring.Webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    // Get all users
    @GetMapping("/allusers")
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
    // Get users by name
    @GetMapping("/name/{name}")
    public Flux<User> getUsersByName(@PathVariable String name) {
        return userRepository.findByName(name);
    }
    @PostMapping()
    public Flux<User> createUser(@RequestBody User user) {
        return generateUsersAutomatically(100);
    }
    @PutMapping("/update")
    public Mono<Void> updateUser(@RequestParam String company){
        return userRepository.updatingColumn(company);
    }
    public Flux<User> generateUsersAutomatically(int count) {
        Flux<User> users = Flux.range(1, count)
                .map(i -> new User(null, "User" + i, "user" + i + "@example.com"));
        return userRepository.saveAll(users);
    }
}
