package com.vst.applicatons.project.repository;

import com.vst.applicatons.project.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{
    /**
     * Нахождение всех записей из таблицы.
     *
     * @return - список объектов - записей данного entity
     * */
    @NotNull
    @Override
    List<User> findAll();

    /**
     * Поиск пользователя по почте (поле email в таблице USERS)
     *
     * @param email - почта пользователя, которого необходимо найти
     * @return пользователь с переданной почтой
     * */
    User findByEmail(String email);
}
