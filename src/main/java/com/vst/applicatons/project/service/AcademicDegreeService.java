package com.vst.applicatons.project.service;

import com.vst.applicatons.project.entity.AcademicDegree;
import com.vst.applicatons.project.repository.AcademicDegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicDegreeService
{
    @Autowired
    private AcademicDegreeRepository academicDegreeRepository;

    public List<AcademicDegree> findAll()
    {
        return academicDegreeRepository.findAll();
    }
}
