package com.vst.applicatons.project.service;

import com.vst.applicatons.project.entity.Cathedra;
import com.vst.applicatons.project.repository.CathedraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CathedraService
{
    @Autowired
    private CathedraRepository cathedraRepository;

    public List<Cathedra> findAll()
    {
        return cathedraRepository.findAll();
    }
}
