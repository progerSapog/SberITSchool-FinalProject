package com.vst.applicatons.project.repository;

import com.vst.applicatons.project.entity.AcademicDegree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicDegreeRepository extends JpaRepository<AcademicDegree, Long>
{
    @Override
    List<AcademicDegree> findAll();
}
