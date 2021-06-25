package com.vst.applications.project.unit_tests.service;

import com.vst.applications.project.entity.User;
import com.vst.applications.project.repository.UserRepository;
import com.vst.applications.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Юнит тестирование UserService
 * @see UserService
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest
{
    @Autowired
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UserRepository userRepository;

    /**
     * Тестирование метода findAll()
     * Получение всех записей из таблицы
     * */
    @Test
    void findAllTest()
    {
        Mockito.doReturn(new ArrayList<User>())
                .when(userRepository)
                .findAll();
        assertNotNull(userRepository.findAll());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    /**
     * Тестирование метода addNewUser()
     * Сохранение записи в таблицу
     * */
    @Test
    void addNewUserTest()
    {
        User user = new User();
        assertTrue(userService.addNewUser(user));
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    /**
     * Тестирование метода addNewUser()
     * Не удачная попытка сохранения записи в таблицу
     * */
    @Test
    void addNewUserFailedTest()
    {
        User user = new User();
        user.setEmail("igor@mail.ru");
        Mockito.doReturn(new User())
                .when(userRepository)
                .findByEmail("igor@mail.ru");
        assertFalse(userService.addNewUser(user));
        Mockito.verify(userRepository, Mockito.times(0)).save(user);
    }

    /**
     * Тестирование метода findByEmail(), использующийся при аутентификации пользователя
     * Получение записи из таблицы по email
     * */
    @Test
    void findByUsernameTest()
    {
        String email = "igor@mail.ru";
        Mockito.doReturn(new User())
                .when(userRepository)
                .findByEmail("igor@mail.ru");
        assertNotNull(userRepository.findByEmail(email));
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);
    }

    /**
     * Тестирование метода findByEmail(), использующийся при аутентификации пользователя
     * Неудачная попытка получение записи из таблицы по email
     * */
    @Test
    void findByUsernameFailedTest()
    {
        String email = "igser@mail.ru";
        Mockito.doReturn(new User())
                .when(userRepository)
                .findByEmail("igor@mail.ru");
        assertNull(userRepository.findByEmail(email));
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);
    }
}