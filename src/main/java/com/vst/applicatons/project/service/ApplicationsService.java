package com.vst.applicatons.project.service;

import com.vst.applicatons.project.entity.Applications;
import com.vst.applicatons.project.repository.ApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для взаимодействия с entity Applications при помощи
 * ApplicationsRepository
 *
 * @see Applications
 * @see ApplicationsRepository
 * */
@Service
public class ApplicationsService
{
    //DI ApplicationsRepository в данный сервис
    @Autowired
    private ApplicationsRepository applicationsRepository;

    /**
     * Получение заявок - всех записей из таблицы
     * APPLICATIONS
     *
     * @return список объектов - записей из таблицы
     * */
    public List<Applications> findAll()
    {
        return applicationsRepository.findAll();
    }
}
