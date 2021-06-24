package com.vst.applications.project.repository;

import com.vst.applications.project.entity.Cathedra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, использующий JPA Entity для взаимодействия с Entity
 * Cathedra
 *
 * @see Cathedra
 * */
public interface CathedraRepository extends JpaRepository<Cathedra, Long>
{
    /**
     * Нахождение всех записей из таблицы.
     *
     * @return - список объектов - записей данного entity
     * */
    @Override
    List<Cathedra> findAll();

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
     * @param cathedra - объект, который необходимо сохроанить.
     * */
    @Override
    Cathedra save(Cathedra cathedra);


    /**
     * Нахождение записи по имени
     *
     * @param name - имя, по которому необходимо найти запись
     * */
    Cathedra findByName(String name);

    /**
     * Поиск записи по id
     *
     * @param id - id записи, которую необходимо найти
     * */
    @Override
    Optional<Cathedra> findById(Long id);
}
