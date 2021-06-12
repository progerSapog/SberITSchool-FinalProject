package com.vst.applicatons.project.service;

import com.vst.applicatons.project.entity.Role;
import com.vst.applicatons.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для взаимодействия с entity Role при помощи
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
    Role findByName(String name)
    {
        return roleRepository.findByName(name);
    }
}
