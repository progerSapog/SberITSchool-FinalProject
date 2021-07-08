package com.vst.applications.project.repository;

import com.vst.applications.project.entity.AcademicDegree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Интерфейс, использующий JPA Entity для взаимодействия с Entity
 * AcademicDegree
 *
 * @see AcademicDegree
 * */
public interface AcademicDegreeRepository extends JpaRepository<AcademicDegree, Long>
{
    /**
     * Нахождение всех записей из таблицы.
     *
     * @return - список объектов - записей данного entity
     * */
    @Override
    List<AcademicDegree> findAll();
}
