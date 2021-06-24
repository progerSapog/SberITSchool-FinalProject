package com.vst.applications.project.service;

import com.vst.applications.project.entity.Applications;
import com.vst.applications.project.repository.ApplicationsRepository;
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

    /**
     * Запись заявки в БД
     *
     * @param application - заявка, которую необходимо внести в таблицу
     * @return true, если заявка успешно добавлена в таблицу
     * */
    public boolean saveApplication(Applications application)
    {
        //сохранение заявки
        applicationsRepository.save(application);

        return true;
    }

    /**
     * Удаление заявки из БД, по id.
     *
     * @param id - id записи, которую необходимо найти. */
    public void deleteApplication(Long id)
    {
        if (applicationsRepository.findById(id).isPresent())
        {
            applicationsRepository.deleteById(id);
        }
    }
}
