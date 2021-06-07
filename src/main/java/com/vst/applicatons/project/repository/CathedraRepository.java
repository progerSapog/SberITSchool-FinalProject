package com.vst.applicatons.project.repository;

import com.vst.applicatons.project.entity.Cathedra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CathedraRepository extends JpaRepository<Cathedra, Long>
{
    @Override
    List<Cathedra> findAll();
}
