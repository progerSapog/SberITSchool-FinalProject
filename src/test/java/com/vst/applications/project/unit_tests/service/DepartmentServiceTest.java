package com.vst.applications.project.unit_tests.service;

import com.vst.applications.project.entity.Department;
import com.vst.applications.project.entity.User;
import com.vst.applications.project.repository.DepartmentRepository;
import com.vst.applications.project.service.DepartmentService;
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
class DepartmentServiceTest
{
    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    /**
     * Тестирование метода findAll()
     * Получение всех записей из таблицы
     * */
    @Test
    void findAllTest()
    {
        Mockito.doReturn(new ArrayList<User>())
                .when(departmentRepository)
                .findAll();
        assertNotNull(departmentService.findAll());
        Mockito.verify(departmentRepository, Mockito.times(1)).findAll();
    }

    /**
     * Тестирование метода saveApplications()
     * Сохранение записи в таблицу
     * */
    @Test
    void saveCathedraTest()
    {
        Department department = new Department();
        assertTrue(departmentService.save(department));
        Mockito.verify(departmentRepository, Mockito.times(1)).save(department);
    }
}