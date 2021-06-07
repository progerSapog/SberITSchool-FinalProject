package com.vst.applicatons.project.controllers;

import com.vst.applicatons.project.entity.Cathedra;
import com.vst.applicatons.project.service.CathedraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CathedraController
{
    @Autowired
    private CathedraService cathedraService;

    @RequestMapping("/allCathedra")
    public String showAllCathedra(Model model)
    {
        List<Cathedra> allCathedra = cathedraService.findAll();

        model.addAttribute("allCathedra", allCathedra);

        return "allCathedra";
    }
}
