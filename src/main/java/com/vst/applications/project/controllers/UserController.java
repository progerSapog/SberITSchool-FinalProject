package com.vst.applications.project.controllers;

import com.vst.applications.project.DTO.UserDTO;
import com.vst.applications.project.entity.User;
import com.vst.applications.project.service.AcademicDegreeService;
import com.vst.applications.project.service.DepartmentService;
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
 * @see RoleService
 * @see DepartmentService
 * @see AcademicDegreeService
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
    private DepartmentService departmentService;

    @Autowired
    private AcademicDegreeService academicDegreeService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Обработка Get запроса /allUsers.
     * Получение списка пользователей из БД, передача их на отображение
     * странице allUsers, переход на данную страницу.
     *
     * @param model - объект для передачи данных с сервера на html страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @GetMapping("/all")
    public String showAllUsers(Model model)
    {
        Map<String, Object> map = Map.ofEntries(
                Map.entry("users", userService.findAll()),
                Map.entry("role", roleService.findByName("ROLE_ADMIN"))
        );
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
        map.put("cathedraList", departmentService.findAll());
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
     * @param  userForm - заполненый объект UserDTO
     *                    @Valid отвечает за валидацию полей при помощи Hibernate - validator
     *                    @ModelAttribute означает, что данный параметр функии мы должны получить из модели,
     *                    отправленной с html страницы после нажатия submit
     * @param bindingResult - интерфейс регистрации ошибок, обнаруженных Hibernate - validator
     * @param model         - объект для передачи данных с сервера на html страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @PostMapping("/update")
    public String addUser(@Valid @ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult, Model model)
    {
        Optional<User> userFromDB = userService.findById(userForm.getId());

        Map<String, List<?>> map = new HashMap<>();
        map.put("cathedraList", departmentService.findAll());
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

        //Если все проверки пройдены успешно, то переписываем данные из DTO
        //в объект класса User
        User user = new User(userForm.getId(), userForm.getEmail(), userForm.getPassword(),
                userForm.getPasswordConfirm(), userForm.getPasswordToChange(), userForm.getFirstName(),
                userForm.getLastName(), userForm.getMiddleName(), userForm.getAcademicDegree(), userForm.getDepartment(),
                userForm.getRoles());

        if (!userService.update(user))
        {
            model.addAttribute("emailChangeError", "Почта уже занята");
            return "updateUser";
        }

        //Перенаправление в случае удачного измнения данных
        return "redirect:/logout";
    }

    /**
     * Обработка POST запроса по адресу /update.
     * Измнение роли пользователя администратором
     *
     * @param id     - id пользователя. @RequestParam - получение параметра из строки запроса
     * @param model  - объект для передачи данных с сервера на html страницу.
     * @return имя страниц
     * */
    @GetMapping("/changeRole")
    public String changeRole(@RequestParam("userId") Long id, Model model)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("userForm", userService.findById(id).get());
        map.put("roles", roleService.findAll());
        model.mergeAttributes(map);

        return "changeRole";
    }

    /**
     * Обработка POST запроса по адресу /update.
     * Измнение роли пользователя администратором
     *
     * @param  userForm - заполненый объект UserDTO
     *                    @Valid отвечает за валидацию полей при помощи Hibernate - validator
     *                    @ModelAttribute означает, что данный параметр функии мы должны получить из модели,
     *                    отправленной с html страницы после нажатия submit
     * @return имя страниц
     * */
    @PostMapping("/changeRole")
    public String changeRole(@Valid @ModelAttribute("userForm") UserDTO userForm)
    {
        userService.save(new User(userForm.getId(), userForm.getEmail(), userForm.getPassword(),
                userForm.getPasswordConfirm(), userForm.getPasswordToChange(), userForm.getFirstName(),
                userForm.getLastName(), userForm.getMiddleName(), userForm.getAcademicDegree(),
                userForm.getDepartment(), userForm.getRoles()));
        return "redirect:/user/all";
    }
}
