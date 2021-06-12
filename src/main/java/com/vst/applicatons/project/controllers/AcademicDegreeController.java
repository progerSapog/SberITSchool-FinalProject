package com.vst.applicatons.project.controllers;

import com.vst.applicatons.project.entity.AcademicDegree;
import com.vst.applicatons.project.service.AcademicDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Контроллер, отвечающий за взаимодействие с entity AcademicDegree
 *
 * @see AcademicDegree
 * @see AcademicDegreeService
 * */
@Controller
public class AcademicDegreeController
{
    //DI AcademicDegreeService в данный контроллер
    @Autowired
    private AcademicDegreeService academicDegreeService;

    /**
     * Обработка запроса allAcademicDegree.
     * Получение списка ученых степеней из БД, передача их на отображение
     * jsp странице allAcademicDegree, переход на данную страницу.
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @RequestMapping("/allAcademicDegree")
    public String showAllAcademicDegree(Model model)
    {
        List<AcademicDegree> allAcademicDegree = academicDegreeService.findAll();
        model.addAttribute("allAcademicDegree", allAcademicDegree);

        return "allAcademicDegree";
    }
}
