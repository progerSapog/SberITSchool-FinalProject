package com.vst.applicatons.project.controllers;

import com.vst.applicatons.project.entity.Applications;
import com.vst.applicatons.project.service.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ApplicationController
{
    @Autowired
    private ApplicationsService applicationsService;

    @RequestMapping("/")
    public String showAllAcademicDegree(Model model)
    {
        List<Applications> allApplications = applicationsService.findAll();

        model.addAttribute("allApplications", allApplications);

        for (int i = 0; i < allApplications.size(); i++)
        {
            System.out.println(allApplications.get(i).getUser().getFirstName());
        }

        return "index";
    }
}
