package com.vst.applications.project.repository;

import com.vst.applications.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Интерфейс, использующий JPA Entity для взаимодействия с Entity
 * RoleDTO
 *
 * @see Role
 * */
public interface RoleRepository extends JpaRepository<Role, Long>
{
    /**
     * Поиск роли по имени (поле name в таблице ROLE)
     *
     * @param name - имя роли, которую необходимо найти
     * @return роль с переданным именем.
     * */
    Role findByName(String name);

    /**
     * Нахождение всех записей из таблицы.
     *
     * @return - список объектов - записей данного entity
     * */
    @Override
    List<Role> findAll();
}
