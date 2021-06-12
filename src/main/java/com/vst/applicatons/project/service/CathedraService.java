package com.vst.applicatons.project.service;

import com.vst.applicatons.project.entity.Cathedra;
import com.vst.applicatons.project.repository.CathedraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для взаимодействия с entity Cathedra при помощи
 * CathedraRepository
 *
 * @see Cathedra
 * @see CathedraRepository
 * */
@Service
public class CathedraService
{
    //DI CathedraRepository в данный сервис
    @Autowired
    private CathedraRepository cathedraRepository;

    /**
     * Получение кафедр - всех записей из таблицы
     * CATHEDRA
     *
     * @return список объектов - записей из таблицы
     * */
    public List<Cathedra> findAll()
    {
        return cathedraRepository.findAll();
    }
}
