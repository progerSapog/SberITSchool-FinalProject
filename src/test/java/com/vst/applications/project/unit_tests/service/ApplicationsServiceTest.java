package com.vst.applications.project.unit_tests.service;

import com.vst.applications.project.entity.Applications;
import com.vst.applications.project.entity.User;
import com.vst.applications.project.repository.ApplicationsRepository;
import com.vst.applications.project.service.ApplicationsService;
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
 * Юнит тестирование ApplicationsService
 * @see ApplicationsService
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationsServiceTest
{
    @Autowired
    private ApplicationsService applicationsService;

    @MockBean
    private ApplicationsRepository applicationsRepository;

    /**
     * Тестирование метода findAll()
     * Получение всех записей из таблицы
     * */
    @Test
    void findAllTest()
    {
        Mockito.doReturn(new ArrayList<User>())
                .when(applicationsRepository)
                .findAll();
        assertNotNull(applicationsService.findAll());
        Mockito.verify(applicationsRepository, Mockito.times(1)).findAll();
    }

    /**
     * Тестирование метода saveApplications()
     * Сохранение записи в таблицу
     * */
    @Test
    void saveApplicationsTest()
    {
        Applications applications = new Applications();
        assertTrue(applicationsService.save(applications));
        Mockito.verify(applicationsRepository, Mockito.times(1)).save(applications);
    }
}