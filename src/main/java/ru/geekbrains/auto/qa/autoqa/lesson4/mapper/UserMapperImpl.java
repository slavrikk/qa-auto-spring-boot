package ru.geekbrains.auto.qa.autoqa.lesson4.mapper;

import org.springframework.stereotype.Component;
import ru.geekbrains.auto.qa.autoqa.lesson2.controller.rest.example.dto.User;
import ru.geekbrains.auto.qa.autoqa.lesson4.entity.UserEntity;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity dtoToEntity(User userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setSecondName(userDto.getSecondName());
        userEntity.setAge(userDto.getAge());
        return userEntity;
    }

    @Override
    public User entityToDto(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setSecondName(userEntity.getSecondName());
        user.setAge(userEntity.getAge());
        return user;
    }
}
