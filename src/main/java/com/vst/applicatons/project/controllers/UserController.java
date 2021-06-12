package com.vst.applicatons.project.controllers;

import com.vst.applicatons.project.entity.User;
import com.vst.applicatons.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Контроллер, отвечающий за взаимодействие с entity User
 *
 * @see User
 * @see UserService
 * */
@Controller
public class UserController
{
    //DI UserService в данный контроллер
    @Autowired
    private UserService userService;

    /**
     * Обработка запроса /allUsers.
     * Получение списка пользователей из БД, передача их на отображение
     * jsp странице allUsers, переход на данную страницу.
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @RequestMapping("/allUsers")
    public String showAllCathedra(Model model)
    {
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);

        return "allUsers";
    }
}
