package com.vst.applications.project.repository;

import com.vst.applications.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс, использующий JPA Entity для взаимодействия с Entity
 * Role
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
}
