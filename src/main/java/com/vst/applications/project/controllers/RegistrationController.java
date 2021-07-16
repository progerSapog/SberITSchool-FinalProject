package com.vst.applications.project.controllers;

import com.vst.applications.project.DTO.UserDTO;
import com.vst.applications.project.entity.User;
import com.vst.applications.project.service.AcademicDegreeService;
import com.vst.applications.project.service.DepartmentService;
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
import java.util.Map;

/**
 * Контроллер, отвечающий за регистрацию.
 *
 * @see UserService
 * @see DepartmentService
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
    private DepartmentService departmentService;

    @Autowired
    private AcademicDegreeService academicDegreeService;

    /**
     * Обработка Get запроса по /registration.
     * Передача в модель списков кафедр и ученых степеней для отображение их
     * в выпадающих списках.
     * Перенаправление на данную страницу
     *
     * @param model - объект для передачи данных с сервера на html страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @GetMapping
    public String registration(Model model)
    {

        /* Добавление в модель пустого User позволит при заполнении формы получить не
           n-ое кол-во полей для обработки, а сразу заполненый объект класса User */
        Map<String, Object> map = Map.ofEntries(
                Map.entry("departmentList", departmentService.findAll()),
                Map.entry("academicDegreeList", academicDegreeService.findAll()),
                Map.entry("userForm", new UserDTO())

        );

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
    public String addNewUser(@Valid @ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult, Model model)
    {
        Map<String, Object> map = Map.ofEntries(
                Map.entry("departmentList", departmentService.findAll()),
                Map.entry("academicDegreeList", academicDegreeService.findAll())
        );
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

        //Если все проверки пройдены успешно, то переписываем данные из DTO
        //в объект класса User
        User user = new User(userForm.getId(), userForm.getEmail(), userForm.getPassword(),
                userForm.getPasswordConfirm(), userForm.getPasswordToChange(), userForm.getFirstName(),
                userForm.getLastName(), userForm.getMiddleName(), userForm.getAcademicDegree(), userForm.getDepartment(),
                null);

        //Если не удалось сохранить данные, то возвращаем пользователя на страницу регистрации,
        //добавив в модель сообщение об том, что такой пользователь уже есть.
        if (!userService.addNewUser(user))
        {
            model.addAttribute("emailError", "Пользователь с данной почтой уже существует");
            return "registration";
        }

        //Перенаправление в случае удачной регистрации
        return "redirect:/";
    }
}
