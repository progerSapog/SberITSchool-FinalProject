package com.vst.applications.project.repository;

import com.vst.applications.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    /**
     * Нахождение всех записей из таблицы.
     *
     * @return - список объектов - записей данного entity
     * */
    @Override
    List<User> findAll();

    /**
     * Поиск пользователя по почте (поле email в таблице USERS)
     *
     * @param email - почта пользователя, которого необходимо найти
     * @return пользователь с переданной почтой
     * */
    User findByEmail(String email);

    /**
     * Сохранение объекта entity User в БД
     *
     * @param user - пользователь, которого необходимо сохранить в БД
     * */
    @Override
    User save(User user);

    /**
     * Удаление из БД по id
     *
     * @param id - id записи, которую необходимо найти.
     * */
    @Override
    void deleteById(Long id);

    /**
     * Поиск записи по id
     *
     * @param id - id записи, которую необходимо найти
     * */
    @Override
    Optional<User> findById(Long id);
}
