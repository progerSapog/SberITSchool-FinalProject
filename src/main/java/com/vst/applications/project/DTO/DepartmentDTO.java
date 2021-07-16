package com.vst.applications.project.DTO;

import com.vst.applications.project.entity.Department;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object для entity
 * Department
 *
 * @see Department
 * */
public class DepartmentDTO
{
    private Long id;

    @NotNull
    @Size(min = 2, message = "Неверная кафедра")
    private String name;

    public DepartmentDTO()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
