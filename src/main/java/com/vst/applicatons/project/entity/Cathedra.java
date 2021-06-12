package com.vst.applicatons.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Класс объектов Кафедра - таблица БД CATHEDRA
 * Для работы с БД entity должен реализовывать getters и setters
 * для всех полей и иметь конструтор по умолчанию.
 * */
@Entity
@Table(name = "CATHEDRA")
public class Cathedra
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cathedra_table")
    @SequenceGenerator(name = "sq_cathedra_table", allocationSize = 1, sequenceName = "sq_cathedra_table")
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
