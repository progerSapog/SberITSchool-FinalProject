package com.vst.applications.project.service;

import com.vst.applications.project.entity.Department;
import com.vst.applications.project.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимодействия с entity Cathedra при помощи
 * CathedraRepository
 *
 * @see Department
 * @see DepartmentRepository
 * */
@Service
public class DepartmentService
{
    //DI CathedraRepository в данный сервис
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Получение кафедр - всех записей из таблицы
     * CATHEDRA
     *
     * @return список объектов - записей из таблицы
     * */
    public List<Department> findAll()
    {
        return departmentRepository.findAll();
    }

    /**
     * Удаление кафедры из БД по id.
     *
     * @param id - id кафедры, которую необходимо удалить */
    public void delete(Long id)
    {
        Optional<Department> cathedraOpt = departmentRepository.findById(id);

        //Перед удалением кафедры необходимо удалить ссылки на эту кафедру
        //у пользователей
        if (cathedraOpt.isPresent())
        {
            cathedraOpt.get().getUsers().forEach(x -> x.setDepartment(null));

            departmentRepository.deleteById(id);
        }
    }

    /**
     * Сохранение кафедры в БД.
     *
     * @param department - кафедра, которую необходимо сохранить в БД.
     * */
    public boolean save(Department department)
    {
        Department departmentToCheck = departmentRepository.findByName(department.getName());

        //Если кафедры с таким именем нет, то добавляем ее,
        //иначе игнорируем попытку добавления
        if (departmentToCheck == null)
        {
            departmentRepository.save(department);
            return true;
        }

        return false;
    }

    /**
     * Нахождение кафедры в БД по id.
     * @param id - id по которому необходимо найти кафедру в БД.
     * */
    public Optional<Department> findById(Long id)
    {
        return departmentRepository.findById(id);
    }
}
