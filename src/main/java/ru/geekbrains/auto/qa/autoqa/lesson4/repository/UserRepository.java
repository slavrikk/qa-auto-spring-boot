package ru.geekbrains.auto.qa.autoqa.lesson4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.auto.qa.autoqa.lesson4.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
