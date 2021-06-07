package com.vst.applicatons.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CATHEDRA")
public class Cathedra
{
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "name")
    @Size(min = 2, message = "Неверная кафедра")
    private String name;

    public Cathedra()
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
