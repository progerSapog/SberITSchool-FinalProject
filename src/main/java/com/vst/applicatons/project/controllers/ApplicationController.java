package com.vst.applicatons.project.controllers;

import com.vst.applicatons.project.entity.Applications;
import com.vst.applicatons.project.service.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Контроллер, отвечающий за взаимодействие с entity Applications
 *
 * @see Applications
 * @see ApplicationsService
 * */
@Controller
public class ApplicationController
{
    //DI ApplicationsService в данный контроллер
    @Autowired
    private ApplicationsService applicationsService;

    /**
     * Обработка запроса /.
     * Получение списка заявок из БД, передача их на отображение
     * jsp странице index, переход на данную страницу.
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @RequestMapping("/")
    public String showAllAcademicDegree(Model model)
    {
        List<Applications> allApplications = applicationsService.findAll();
        model.addAttribute("allApplications", allApplications);

        return "index";
    }
}
