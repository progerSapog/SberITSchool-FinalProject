package com.vst.applications.project.service;

import com.vst.applications.project.entity.Role;
import com.vst.applications.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для взаимодействия с entity RoleDTO при помощи
 * RoleRepository
 *
 * @see Role
 * @see RoleRepository
 * */
@Service
public class RoleService
{
    //DI RoleRepository в данный сервис
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Поиск роли по переданному имени
     *
     * @param name - имя роли, котрую необходимо найти
     * @return объект роль - запись из таблицы с данным именем
     * */
    public Role findByName(String name)
    {
        return roleRepository.findByName(name);
    }

    /**
     * Получение всхе записей из таблицы
     *
     * @return список всех записей.
     * */
    public List<Role> findAll()
    {
        return roleRepository.findAll();
    }
}
