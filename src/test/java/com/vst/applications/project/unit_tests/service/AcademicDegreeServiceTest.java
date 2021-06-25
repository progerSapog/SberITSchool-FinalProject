package com.vst.applications.project.unit_tests.service;

import com.vst.applications.project.entity.User;
import com.vst.applications.project.repository.AcademicDegreeRepository;
import com.vst.applications.project.service.AcademicDegreeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Юнит тестирование AcademicDegreeService
 * @see AcademicDegreeService
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
class AcademicDegreeServiceTest
{
    @Autowired
    private AcademicDegreeService academicDegreeService;

    @MockBean
    private AcademicDegreeRepository academicDegreeRepository;

    /**
     * Тестирование метода findAll()
     * Получение всех записей из таблицы
     * */
    @Test
    void findAll()
    {
        Mockito.doReturn(new ArrayList<User>())
                .when(academicDegreeRepository)
                .findAll();
        assertNotNull(academicDegreeService.findAll());
        Mockito.verify(academicDegreeRepository, Mockito.times(1)).findAll();
    }
}