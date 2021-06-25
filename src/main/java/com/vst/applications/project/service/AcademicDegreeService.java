package com.vst.applications.project.service;

import com.vst.applications.project.entity.AcademicDegree;
import com.vst.applications.project.repository.AcademicDegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для взаимодействия с entity AcademicDegree при помощи
 * AcademicDegreeRepository
 *
 * @see AcademicDegree
 * @see AcademicDegreeRepository
 * */
@Service
public class AcademicDegreeService
{
    //DI AcademicDegreeRepository в данный сервис
    @Autowired
    private AcademicDegreeRepository academicDegreeRepository;

    /**
     * Получение ученых степеней - всех записей из таблицы
     * ACADEMIC_DEGREE
     *
     * @return список объектов - записей из таблицы
     * */
    public List<AcademicDegree> findAll()
    {
        return academicDegreeRepository.findAll();
    }
}