package com.vst.applications.project.controllers;

import com.vst.applications.project.entity.Role;
import com.vst.applications.project.entity.User;
import com.vst.applications.project.service.AcademicDegreeService;
import com.vst.applications.project.service.CathedraService;
import com.vst.applications.project.service.RoleService;
import com.vst.applications.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Контроллер, отвечающий за взаимодействие с entity User
 *
 * @see User
 * @see UserService
 * */
@Controller
@RequestMapping("/user")
public class UserController
{
    //DI необходимы сервисов и других бинов.
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CathedraService cathedraService;

    @Autowired
    private AcademicDegreeService academicDegreeService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Обработка Get запроса /allUsers.
     * Получение списка пользователей из БД, передача их на отображение
     * jsp странице allUsers, переход на данную страницу.
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @GetMapping("/all")
    public String showAllUsers(Model model)
    {
        List<User> allUsers = userService.findAll();
        Role role = roleService.findByName("ROLE_ADMIN");

        Map<String, Object> map = new HashMap<>();

        map.put("roleAdmin", role);
        map.put("users", allUsers);

        model.addAttribute("roleAdmin", role);
        model.mergeAttributes(map);

        return "allUsers";
    }

    /**
     * Обработка Post запроса по /all.
     * Поскольку стандартные html формы поддерживают только методы POST/GET, то для
     * удаления используем метод POST, внутри которого передана дополнительная строка
     * с описанием действия - delete
     *
     * @param userId - id пользователя. @RequestParam - получение параметра из строки запроса
     * @param action - строка с описанием действия. @RequestParam - получение параметра из строки запроса
     * @return имя страниц
     * */
    @PostMapping("/all")
    public String deleteUser(@RequestParam(defaultValue = "") Long userId,
                             @RequestParam(defaultValue = "") String action)
    {
        if (action.equals("delete"))
        {
            userService.deleteUser(userId);
        }

        return "redirect:/user/all";
    }

    /**
     * Обработка get запросы по /update
     * В модель передаем текущего залогиненного пользователя,
     * списки ученых степеней и кафедр
     *
     * @param user  - текущий залогиненых пользователь
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @GetMapping("/update")
    private String updateUser(@AuthenticationPrincipal User user, Model model)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("userForm", user);
        map.put("cathedraList", cathedraService.findAll());
        map.put("academicDegreeList", academicDegreeService.findAll());

        //Чтобы форма с паролем была изначальна пуста, устнаваливаем пароль = null
        user.setPassword(null);

        model.mergeAttributes(map);
        return "updateUser";
    }

    /**
     * Обработка Post запроса по /update.
     * Измнение данныех пользователя
     *
     * @param  userForm - заполненый объект entity User
     *                    @Valid отвечает за валидацию полей при помощи Hibernate - validator
     *                    @ModelAttribute означает, что данный параметр функии мы должны получить из модели,
     *                    отправленной с jsp/html страницы после нажатия submit
     * @param bindingResult - интерфейс регистрации ошибок, обнаруженных Hibernate - validator
     * @param model         - объект для передачи данных с сервера на html/jsp страницу.
     *
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @PostMapping("/update")
    public String addUser(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model)
    {
        Optional<User> userFromDB = userService.findById(userForm.getId());

        Map<String, List> map = new HashMap<>();
        map.put("cathedraList", cathedraService.findAll());
        map.put("academicDegreeList", academicDegreeService.findAll());
        model.mergeAttributes(map);

        //Если Hibernate - validator нашел ошибки, то возвращаем пользователя
        //на страницу регистрации
        if (bindingResult.hasErrors())
        {
            return "updateUser";
        }

        //Если старый введенный пароль не совпадает с паролем в БД, то возвращаем пользователя
        //на эту же страницу с соответсвующим сообщением.
        if (userFromDB.isPresent() &&
                !bCryptPasswordEncoder.matches(userForm.getPassword(), userFromDB.get().getPassword()))
        {
            model.addAttribute("oldPasswordError", "Неверный пароль");
            return "updateUser";
        }

        //Если пароли не совпадают, то возвращаем пользователя на страницу регистрации,
        //добавив в модель сообщение об ошибке пароля.
        if (!userForm.getPasswordToChange().equals(userForm.getPasswordConfirm()))
        {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "updateUser";
        }

        if (!userForm.getPasswordToChange().isEmpty())
        {
            userForm.setPassword(userForm.getPasswordToChange());
        }

        if (!userService.save(userForm))
        {
            model.addAttribute("emailChangeError", "Почта уже занята");
            return "updateUser";
        }

        //Перенаправление в случае удачного измнения данных
        return "redirect:/logout";
    }
}
