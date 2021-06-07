package com.vst.applicatons.project.controllers;

import com.vst.applicatons.project.entity.AcademicDegree;
import com.vst.applicatons.project.entity.Cathedra;
import com.vst.applicatons.project.service.AcademicDegreeService;
import com.vst.applicatons.project.service.CathedraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AcademicDegreeController
{
    @Autowired
    private AcademicDegreeService academicDegreeService;

    @RequestMapping("/allAcademicDegree")
    public String showAllAcademicDegree(Model model)
    {
        List<AcademicDegree> allAcademicDegree = academicDegreeService.findAll();

        model.addAttribute("allAcademicDegree", allAcademicDegree);

        return "allAcademicDegree";
    }
}
