package com.vst.applications.project.controllers;

import com.vst.applications.project.entity.User;
import com.vst.applications.project.service.AcademicDegreeService;
import com.vst.applications.project.service.CathedraService;
import com.vst.applications.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер, отвечающий за регистрацию.
 *
 * @see UserService
 * @see CathedraService
 * @see AcademicDegreeService
 * */
@Controller
@RequestMapping("/registration")
public class RegistrationController
{
    //DI необходимых сервисов
    @Autowired
    private UserService userService;

    @Autowired
    private CathedraService cathedraService;

    @Autowired
    private AcademicDegreeService academicDegreeService;

    /**
     * Обработка Get запроса по /registration.
     * Передача в модель списков кафедр и ученых степеней для отображение их
     * в выпадающих списках.
     * Перенаправление на данную страницу
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @GetMapping
    public String registration(Model model)
    {
        Map<String, List> map = new HashMap<>();
        map.put("cathedraList", cathedraService.findAll());
        map.put("academicDegreeList", academicDegreeService.findAll());

        /* Добавление в модель пустого User позволит при заполнении формы получить не
           n-ое кол-во полей для обработки, а сразу заполненый объект класса User */
        model.addAttribute("userForm", new User());

        //Добавление к модели map, содержащей списки
        model.mergeAttributes(map);

        return "registration";
    }

    /**
     * Обработка Post запроса по /registration.
     * Передача в модель списков кафедр и ученых степеней для отображение их
     * в выпадающих списках, поскольку при перенаправлении после обратки POST запроса
     * списки на странице не сохраняются.
     *
     * @param userForm - заполненый объект entity User
     *                   @Valid отвечает за валидацию полей entity User при помощи Hibernate - validator
     *                   @ModelAttribute означает, что данный параметр функии мы должны получить из модели,
     *                   отправленной с jsp/html страницы после нажатия submit
     * @param bindingResult - интерфейс регистрации ошибок, обнаруженных Hibernate - validator
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     *
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @PostMapping
    public String addNewUser(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model)
    {
        Map<String, List> map = new HashMap<>();
        map.put("cathedraList", cathedraService.findAll());
        map.put("academicDegreeList", academicDegreeService.findAll());
        model.mergeAttributes(map);

        //Если Hibernate - validator нашел ошибки, то возвращаем пользователя
        //на страницу регистрации
        if (bindingResult.hasErrors())
        {
            return "registration";
        }

        //Если пароли не совпадают, то возвращаем пользователя на страницу регистрации,
        //добавив в модель сообщение об ошибке пароля.
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm()))
        {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        //Если не удалось сохранить данные, то возвращаем пользователя на страницу регистрации,
        //добавив в модель сообщение об том, что такой пользователь уже есть.
        if (!userService.addNewUser(userForm))
        {
            model.addAttribute("emailError", "Пользователь с данной почтой уже существует");
            return "registration";
        }

        //Перенаправление в случае удачной регистрации
        return "redirect:/";
    }
}
