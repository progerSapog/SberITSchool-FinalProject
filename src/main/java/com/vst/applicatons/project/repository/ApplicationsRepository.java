package com.vst.applicatons.project.repository;

import com.vst.applicatons.project.entity.Applications;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
    @NotNull
    @Override
    List<Applications> findAll();
}
