package com.vst.applicatons.project.controllers;

import com.vst.applicatons.project.entity.Cathedra;
import com.vst.applicatons.project.service.CathedraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Контроллер, отвечающий за взаимодействие с entity Cathedra
 *
 * @see Cathedra
 * @see CathedraService
 * */
@Controller
public class CathedraController
{
    //DI CathedraService в данный контроллер
    @Autowired
    private CathedraService cathedraService;

    /**
     * Обработка запроса /allCathedra.
     * Получение списка кафедр из БД, передача их на отображение
     * jsp странице allCathedra, переход на данную страницу.
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @RequestMapping("/allCathedra")
    public String showAllCathedra(Model model)
    {
        List<Cathedra> allCathedra = cathedraService.findAll();
        model.addAttribute("allCathedra", allCathedra);

        return "allCathedra";
    }
}
