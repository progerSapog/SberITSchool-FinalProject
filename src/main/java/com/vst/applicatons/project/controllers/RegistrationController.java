package com.vst.applicatons.project.controllers;

import com.vst.applicatons.project.entity.AcademicDegree;
import com.vst.applicatons.project.entity.Cathedra;
import com.vst.applicatons.project.entity.User;
import com.vst.applicatons.project.models.ModelAttributes;
import com.vst.applicatons.project.service.AcademicDegreeService;
import com.vst.applicatons.project.service.CathedraService;
import com.vst.applicatons.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController
{
    @Autowired
    private UserService userService;

    @Autowired
    private CathedraService cathedraService;

    @Autowired
    private AcademicDegreeService academicDegreeService;

    @GetMapping("/registration")
    public String registration(Model model)
    {
        List<Cathedra> cathedraList = cathedraService.findAll();
        List<AcademicDegree> academicDegreeList = academicDegreeService.findAll();

        ModelAttributes modelAttributes = new ModelAttributes(new User(), cathedraList, academicDegreeList);
        model.addAttribute("userForm", modelAttributes);

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userFrom") @Valid User userForm, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "registration";
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm()))
        {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        return "redirect:/";
    }
}
