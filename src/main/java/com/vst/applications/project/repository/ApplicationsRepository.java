package com.vst.applications.project.repository;

import com.vst.applications.project.entity.Applications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, использующий JPA Entity для взаимодействия с Entity
 * Applications
 *
 * @see Applications
 * */
public interface ApplicationsRepository extends JpaRepository<Applications, Long>
{
    /**
     * Нахождение всех записей из таблицы.
     *
     * @return - список объектов - записей данного entity
     * */
    @Override
    List<Applications> findAll();

    /**
     * Сохранение в БД объекта entity Applications
     *
     * @param applications - запись, которую необходимо сохранить
     * */
    @Override
    Applications save(Applications applications);

    /**
     * Удаление из БД по id
     *
     * @param id - id записи, которую необходимо удалить.
     * */
    @Override
    void deleteById(Long id);

    /**
     * Найти запись в таблицы по id
     *
     * @param id - id записи, которую необходимо найти.
     * */
    @Override
    Optional<Applications> findById(Long id);
}
