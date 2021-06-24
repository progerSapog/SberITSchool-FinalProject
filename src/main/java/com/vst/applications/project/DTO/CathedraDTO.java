package com.vst.applications.project.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object для entity Cathedra.
 * Используется для передачи данных между клиенской и серверной частями.
 * Осуществляет валидацию данных.
 *
 * @see com.vst.applications.project.entity.Cathedra
 */
public class CathedraDTO
{
    private Long id;

    @NotNull
    @Size(min = 2, message = "Неверная кафедра")
    private String name;

    public CathedraDTO()
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
