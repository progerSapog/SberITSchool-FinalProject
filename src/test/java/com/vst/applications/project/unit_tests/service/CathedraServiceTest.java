package com.vst.applications.project.unit_tests.service;

import com.vst.applications.project.entity.Cathedra;
import com.vst.applications.project.entity.User;
import com.vst.applications.project.repository.CathedraRepository;
import com.vst.applications.project.service.CathedraService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class CathedraServiceTest
{
    @Autowired
    private CathedraService cathedraService;

    @MockBean
    private CathedraRepository cathedraRepository;

    /**
     * Тестирование метода findAll()
     * Получение всех записей из таблицы
     * */
    @Test
    void findAllTest()
    {
        Mockito.doReturn(new ArrayList<User>())
                .when(cathedraRepository)
                .findAll();
        assertNotNull(cathedraService.findAll());
        Mockito.verify(cathedraRepository, Mockito.times(1)).findAll();
    }

    /**
     * Тестирование метода saveApplications()
     * Сохранение записи в таблицу
     * */
    @Test
    void saveCathedraTest()
    {
        Cathedra cathedra = new Cathedra();
        assertTrue(cathedraService.saveCathedra(cathedra));
        Mockito.verify(cathedraRepository, Mockito.times(1)).save(cathedra);
    }
}