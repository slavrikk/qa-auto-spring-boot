package ru.geekbrains.auto.qa.autoqa.lesson4.service;

import ru.geekbrains.auto.qa.autoqa.lesson2.controller.rest.example.dto.User;

public interface UserService {

    void save(User userDto);

    void update(User userDto);

    User getById(Integer id);

    void delete(Integer id);
}
