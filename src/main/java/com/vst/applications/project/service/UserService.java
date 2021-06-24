package com.vst.applications.project.service;

import com.vst.applications.project.entity.User;
import com.vst.applications.project.repository.RoleRepository;
import com.vst.applications.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимодействия с entity User при помощи
 * UserRepository
 *
 * @see User
 * @see RoleRepository
 * @see UserRepository
 * @see EntityManager
 * @see BCryptPasswordEncoder
 * */
@Service
public class UserService implements UserDetailsService
{
    //DI необходимых сервисов и бинов.
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    /**
     * Перегрузка метода loadUserByUsername интерфейса UserDetailsService,
     * используемого для авторизации.
     *
     * @param email - почта, по которой необходимо найти пользователя
     * */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(email);

        if (user == null) throw new UsernameNotFoundException("Invalid username or password");

        return user;
    }

    /**
     * Запись пользователя в БД
     *
     * @param user - пользователь, которого необходимо внести в таблицу
     * @return true, если пользователь успешно добавлен в таблицу
     * */
    public boolean saveUser(User user)
    {
        //Попытка найти пользователя с такой почто в БД
        //Если такой пользователь есть, то возвращаем false
        User userBD = userRepository.findByEmail(user.getEmail());
        if (userBD != null) return false;

        //Задаем роль пользователю. По умолчанию все новые пользователи имею статус - ROLE_USER
        if (user.getRoles() == null)
        {
            user.setRoles(Collections.singleton(roleService.findByName("ROLE_USER")));
        }

        //Запись хэшированного пароля
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        //сохранение пользователя
        userRepository.save(user);

        return true;
    }

    public boolean save(User user)
    {
        User userBD = userRepository.findByEmail(user.getEmail());
        if (userBD != null && !userBD.equals(user)) return false;
        //Запись хэшированного пароля
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        //сохранение пользователя
        userRepository.save(user);
        return true;
    }

    /**
     * Получение пользователей - всех записей из таблицы
     * USERS
     *
     * @return список объектов - записей из таблицы
     * */
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    /**
     * Удаление пользователя по id.
     *
     * @param id - id пользователя, которого необходимо удалить.
     * */
    public void deleteUser(Long id)
    {
        if (userRepository.findById(id).isPresent())
        {
            userRepository.deleteById(id);
        }
    }

    /**
     * Нохождение пользователя по id
     *
     * @param id - id пользователя, которого необходимо найти.
     * */
    public Optional<User> findById(Long id)
    {
        return userRepository.findById(id);
    }
}
