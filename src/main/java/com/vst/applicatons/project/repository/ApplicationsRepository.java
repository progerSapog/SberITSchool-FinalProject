package com.vst.applicatons.project.repository;

import com.vst.applicatons.project.entity.Applications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationsRepository extends JpaRepository<Applications, Long>
{
    @Override
    List<Applications> findAll();
}
