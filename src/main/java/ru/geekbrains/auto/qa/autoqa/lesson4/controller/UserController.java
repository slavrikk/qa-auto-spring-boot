package ru.geekbrains.auto.qa.autoqa.lesson4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.auto.qa.autoqa.lesson2.controller.rest.example.dto.User;
import ru.geekbrains.auto.qa.autoqa.lesson4.service.UserService;

@RestController
@RequestMapping("/user-rest")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    void save(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping
    void update(@RequestBody User user) {
        userService.update(user);
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable Integer id) {
        userService.delete(id);
    }
}
