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
import java.util.List;
import java.util.Optional;

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
     * jsp странице allCathedra, переход на данную страницу.
     *
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     * @return имя страницы, на которую будет перенправлен пользователь
     */
    @GetMapping("/all")
    public String showAllCathedra(Model model)
    {
        List<Department> allCathedra = departmentService.findAll();
        model.addAttribute("allCathedra", allCathedra);

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
     *
     * @return перенравление по другому адресу
     * */
    @PostMapping("/all")
    public String deleteCathedra(@RequestParam(defaultValue = "") Long cathedraId,
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
     * @return имя страницы, на которую будет перенправлен пользователь
     * */
    @GetMapping("/add")
    public String addNewCathedra(Model model)
    {
        model.addAttribute("cathedraForAdd", new DepartmentDTO());
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
    public String saveCathedra(@Valid @ModelAttribute("cathedraForAdd") DepartmentDTO departmentDTO, BindingResult bindingResult)
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

    /**
     * Обработка Get запроса по /update. @RequestParam - получение значения из строки запроса.
     * @param id    - id кафедры для изменения.
     * @param model - объект для передачи данных с сервера на html/jsp страницу.
     *
     * @return перенравление по другому адресу
     * */
    @GetMapping("/update")
    public String updateCathedra(@RequestParam("departmentId") Long id, Model model)
    {
        Optional<Department> cathedraOpt = departmentService.findById(id);
        cathedraOpt.ifPresent(cathedra -> model.addAttribute("cathedraForAdd", cathedra));

        return "addDepartment";
    }
}
