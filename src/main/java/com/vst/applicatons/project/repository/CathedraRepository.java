package com.vst.applicatons.project.repository;

import com.vst.applicatons.project.entity.Cathedra;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
    @NotNull
    @Override
    List<Cathedra> findAll();
}
