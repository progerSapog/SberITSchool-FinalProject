package com.vst.applicatons.project.controllers;

import com.vst.applicatons.project.entity.Cathedra;
import com.vst.applicatons.project.entity.User;
import com.vst.applicatons.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping("/allUsers")
    public String showAllCathedra(Model model)
    {
        List<User> allUsers = userService.findAll();

        model.addAttribute("allUsers", allUsers);

        return "allUsers";
    }
}
