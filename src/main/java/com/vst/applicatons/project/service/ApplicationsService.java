package com.vst.applicatons.project.service;

import com.vst.applicatons.project.entity.Applications;
import com.vst.applicatons.project.repository.ApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationsService
{
    @Autowired
    private ApplicationsRepository applicationsRepository;

    public List<Applications> findAll()
    {
        return applicationsRepository.findAll();
    }
}
