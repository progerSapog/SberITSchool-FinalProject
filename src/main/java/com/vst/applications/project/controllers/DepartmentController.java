package com.vst.applications.project.controllers;

import com.vst.applications.project.DTO.DepartmentDTO;
import com.vst.applications.project.entity.Department;
import com.vst.applications.project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Контроллер, отвечающий за взаимодействие с entity Cathedra
 *
 * @see Department
 * @see DepartmentService
 * */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    //DI CathedraService в данный контроллер
    @Autowired
    private DepartmentService departmentService;

    /**
     * Обработка Get запроса /all.
     * Получение списка кафедр из БД, передача их на отображение
     * странице allCathedra, переход на данную страницу.
     *
     * @param model - объект для передачи данных с сервера на html страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     */
    @GetMapping("/all")
    public String showAllDepartments(Model model)
    {
        model.addAttribute("allDepartments", departmentService.findAll());
        return "allDepartment";
    }

    /**
     * Обработка Post запроса /all
     * Поскольку стандартные html формы поддерживают только методы POST/GET, то для
     * удаления используем метод POST, внутри которого передана дополнительная строка
     * с описанием действия - delete
     *
     * @param cathedraId - id кафедры. @RequestParam - получение параметра из строки запроса
     * @param action     - строка с описанием действия. @RequestParam - получение параметра из строки запроса
     * @return перенравление по другому адресу
     * */
    @PostMapping("/all")
    public String deleteDepartment(@RequestParam(defaultValue = "") Long cathedraId,
                                   @RequestParam(defaultValue = "") String action)
    {
        if (action.equals("delete"))
        {
            departmentService.delete(cathedraId);
        }

        return "redirect:/department/all";
    }

    /**
     * Обработка Get запроса /add
     * Передача в модель пустого объекта Кафедра,
     * переход на страницу addCathedra
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенаправлен пользователь
     * */
    @GetMapping("/add")
    public String addDepartment(Model model)
    {
        model.addAttribute("departmentForAdd", new DepartmentDTO());
        return "addDepartment";
    }

    /**
     * Обработка Get запроса по /update.
     * @param id    - id кафедры для изменения. @RequestParam - получение значения из строки запроса.
     * @param model - объект для передачи данных с сервера на html страницу.
     * @return имя страницы, на которую будет перенаправлен пользователь
     * */
    @GetMapping("/update")
    public String updateDepartment(@RequestParam("departmentId") Long id, Model model)
    {
        departmentService.findById(id).ifPresent(department -> model.addAttribute("departmentForAdd", department));
        return "addDepartment";
    }

    /**
     * Обработка Post запроса по /add.
     *
     * @param departmentDTO - заполненый объект dto Cathedra
     *                        @Valid отвечает за валидацию полей при помощи Hibernate - validator
     *                        @ModelAttribute означает, что данный параметр функии мы должны получить из модели,
     *                        отправленной с jsp/html страницы после нажатия submit
     * @param bindingResult - интерфейс регистрации ошибок, обнаруженных Hibernate - validator
     * @return перенравление по другому адресу
     * */
    @PostMapping("/save")
    public String saveDepartment(@Valid @ModelAttribute("departmentForAdd") DepartmentDTO departmentDTO, BindingResult bindingResult)
    {
        //Если Hibernate - validator нашел ошибки, то возвращаем пользователя
        //обратно на эту же страницу
        if (bindingResult.hasErrors())
        {
            return "addDepartment";
        }

        departmentService.save(new Department(departmentDTO.getId(), departmentDTO.getName()));
        return "redirect:/department/all";
    }
}
