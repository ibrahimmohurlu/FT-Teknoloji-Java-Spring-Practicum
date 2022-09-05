package com.example.case1.User.Repository;

import com.example.case1.User.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest {
    // testing CRUD operations
    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        //constructing user object that will be used in test methods
        user = new User();
        user.setFirstName("ibrahim");
        user.setLastName("mohurlu");
        user.setEmail("him@gmail.com");
        user.setPhoneNumber("999-123-1234");
    }

    @Test
    void saveUser() {
        userRepository.save(user);
        long count = userRepository.count();
        assertThat(count > 0);
    }

    @Test
    void deleteUser() {
        //saving a user
        userRepository.save(user);

        // to make sure that we count is not 0 because database is empty.
        long count = userRepository.count();
        assertThat(count > 0);

        //after saving deleting it.
        userRepository.delete(user);

        count = userRepository.count();
        assertThat(count == 0);

    }
    @Test
    void updateUser(){
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findOne(Example.of(user));
        foundUser.get().setFirstName("h.ibrahim");
        userRepository.flush();
        // same user shouldn't exist anymore.
        assertThat(!userRepository.exists(Example.of(user)));

    }

    @Test
    void readUser(){
        userRepository.save(user);
        assertThat(userRepository.count()>0);
    }


}