package com.vst.applications.project.controllers;

import com.vst.applications.project.DTO.ApplicationsDTO;
import com.vst.applications.project.entity.Applications;
import com.vst.applications.project.entity.User;
import com.vst.applications.project.service.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер, отвечающий за взаимодействие с entity Applications
 *
 * @see Applications
 * @see ApplicationsService
 * */
@Controller
@RequestMapping("/applications")
public class ApplicationController
{
    //DI ApplicationsService в данный контроллер
    @Autowired
    private ApplicationsService applicationsService;

    /**
     * Обработка get запроса /all.
     * Получение списка заявок из БД, передача их на отображение
     * jsp странице allApplications, переход на данную страницу.
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенаправлен пользователь
     * */
    @GetMapping("/all")
    public String showAllApplications(@AuthenticationPrincipal User user, Model model)
    {
        List<Applications> allApplications = applicationsService.findAll();

        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("allApplications", allApplications);

        model.mergeAttributes(map);

        return "allApplications";
    }

    /**
     * Обработка Post запроса по /all.
     * Поскольку стандартные html формы поддерживают только методы POST/GET, то для
     * удаления используем метод POST, внутри которого передана дополнительная строка
     * с описанием действия - delete
     *
     * @param applicationId - id заявки. @RequestParam - получение параметра из строки запроса
     * @param action        - строка с описанием действия. @RequestParam - получение параметра из строки запроса
     *
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @PostMapping("/all")
    public String deleteApplication(@RequestParam(defaultValue = "") Long applicationId,
                                    @RequestParam(defaultValue = "") String action)
    {
        if (action.equals("delete"))
        {
            applicationsService.deleteApplication(applicationId);
        }
        return "redirect:/applications/all";
    }

    /**
     * Обработка Get запроса по /add.
     * В модель добавляем пустой Applications.
     * Перенаправление на данную страницу
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @GetMapping("/add")
    public String addApplications(Model model)
    {
        model.addAttribute("appForm", new ApplicationsDTO());
        return "addApplications";
    }

    /**
     * Обработка Post запроса по /add.
     *
     * @param  appForm - заполненый объект entity Applications
     *                    @Valid отвечает за валидацию полей entity Applications при помощи Hibernate - validator
     *                    @ModelAttribute означает, что данный параметр функии мы должны получить из модели,
     *                    отправленной с jsp/html страницы после нажатия submit
     * @param bindingResult - интерфейс регистрации ошибок, обнаруженных Hibernate - validator
     * @param model         - объект для передачи данных с сервера на html/jsp страницу.
     *
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @PostMapping("/add")
    public String addApplications(@AuthenticationPrincipal User user,
                                 @Valid @ModelAttribute("appForm") ApplicationsDTO appForm,
                                 BindingResult bindingResult, Model model)
    {
        //Если Hibernate - validator нашел ошибки, то возвращаем пользователя
        //обратно на эту же страницу
        if (bindingResult.hasErrors())
        {
            return "addApplications";
        }

        Applications applications = new Applications(null, appForm.getAudienceNumber(), appForm.getText());
        applications.setUser(user);

        //Если не удалось сохранить данные, то возвращаем пользователя на страницу регистрации,
        //добавив в модель сообщение об том, что такой пользователь уже есть.
        if (!applicationsService.saveApplication(applications))
        {
            model.addAttribute("appError", "При создании заявки произошла ошибка");
            return "addApplications";
        }

        //Перенаправление в случае удачной регистрации
        return "redirect:/applications/all";
    }
}
