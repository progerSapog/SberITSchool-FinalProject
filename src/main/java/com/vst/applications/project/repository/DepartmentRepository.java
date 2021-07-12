package com.vst.applications.project.repository;

import com.vst.applications.project.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, использующий JPA Entity для взаимодействия с Entity
 * Cathedra
 *
 * @see Department
 * */
public interface DepartmentRepository extends JpaRepository<Department, Long>
{
    /**
     * Нахождение всех записей из таблицы.
     *
     * @return - список объектов - записей данного entity
     * */
    @Override
    List<Department> findAll();

    /**
     * Удаление из БД по id
     *
     * @param id - id записи, которую необходимо найти.
     * */
    @Override
    void deleteById(Long id);

    /**
     * Сохранение объекта в БД
     *
     * @param department - объект, который необходимо сохроанить.
     * */
    @Override
    Department save(Department department);


    /**
     * Нахождение записи по имени
     *
     * @param name - имя, по которому необходимо найти запись
     * */
    Department findByName(String name);

    /**
     * Поиск записи по id
     *
     * @param id - id записи, которую необходимо найти
     * */
    @Override
    Optional<Department> findById(Long id);
}
