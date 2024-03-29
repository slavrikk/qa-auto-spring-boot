package ru.geekbrains.auto.qa.autoqa.lesson6;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.geekbrains.auto.qa.autoqa.lesson4.entity.AddressEntity;
import ru.geekbrains.auto.qa.autoqa.lesson4.entity.UserEntity;
import ru.geekbrains.auto.qa.autoqa.lesson4.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveUserTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(25);
        userEntity.setFirstName("Alex");
        userEntity.setSecondName("Brown");

        userRepository.saveAndFlush(userEntity);

        List<UserEntity> userEntityList = userRepository.findAll();

        Assertions.assertThat(userEntityList.size()).isEqualTo(1);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(userEntityList.get(0).getFirstName()).isEqualTo("Alex");
            s.assertThat(userEntityList.get(0).getSecondName()).isEqualTo("Brown");
            s.assertThat(userEntityList.get(0).getAge()).isEqualTo(25);
        });
    }

    @Test
    public void updateUserTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(25);
        userEntity.setFirstName("Alex");
        userEntity.setSecondName("Brown");
        testEntityManager.persistAndFlush(userEntity);

        UserEntity savedUserEntity = userRepository.findById(userEntity.getId())
                .orElseThrow();

        savedUserEntity.setSecondName("Green");

        userRepository.saveAndFlush(savedUserEntity);

        UserEntity updatedUserEntity = userRepository.findById(userEntity.getId())
                .orElseThrow();

        Assertions.assertThat(updatedUserEntity.getSecondName()).isEqualTo("Green");
    }

    @Test
    public void deleteUserTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(25);
        userEntity.setFirstName("Alex");
        userEntity.setSecondName("Brown");
        testEntityManager.persistAndFlush(userEntity);

        List<UserEntity> userEntityList = userRepository.findAll();
        Assertions.assertThat(userEntityList.size()).isEqualTo(1);

        userRepository.deleteById(userEntity.getId());

        List<UserEntity> userEntityDeletedList = userRepository.findAll();
        Assertions.assertThat(userEntityDeletedList.isEmpty()).isTrue();
    }

    @Test
    public void findUserByCity() {

        UserEntity userEntity = new UserEntity();
        userEntity.setAge(25);
        userEntity.setFirstName("Alex");
        userEntity.setSecondName("Brown");

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity("Moscow");
        testEntityManager.persistAndFlush(addressEntity);

        userEntity.setAddressEntity(addressEntity);
        testEntityManager.persistAndFlush(userEntity);

        Optional<UserEntity> entity = userRepository.findByAddressEntityCity("Moscow");

        Assertions.assertThat(entity.isPresent()).isTrue();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(entity.get().getFirstName()).isEqualTo("Alex");
            s.assertThat(entity.get().getSecondName()).isEqualTo("Brown");
            s.assertThat(entity.get().getAge()).isEqualTo(25);
        });
    }

    @Test
    @Disabled
    void getUserByFirstNameAndSecondNameTest() {

    }
}
