package com.geekbrains.july.market;

import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.repositories.UsersRepository;
import com.geekbrains.july.market.services.UsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UsersService userService;

    @MockBean
    private UsersRepository userRepository;

    @Test
    public void findOneUserTest() {
        User userFromDB = new User();
        userFromDB.setFirstName("John");
        userFromDB.setEmail("john@mail.ru");
        userFromDB.setPhone("123456789");

        Mockito.doReturn(Optional.of(userFromDB))
                .when(userRepository)
                .findOneByPhone("123456789");

        User userJohn = userService.findByPhone("123456789").get();
        Assertions.assertNotNull(userJohn);
        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.eq("123456789"));
//        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.any(String.class));
    }
}
