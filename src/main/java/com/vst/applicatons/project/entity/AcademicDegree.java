package com.vst.applicatons.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Класс объектов Ученая степень - таблица БД ACADEMIC_DEGREE
 * Для работы с БД entity должен реализовывать getters и setters
 * для всех полей и иметь конструтор по умолчанию.
 * */
@Entity
@Table(name = "ACADEMIC_DEGREE")
public class AcademicDegree
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_degree_table")
    @SequenceGenerator(name = "sq_degree_table", allocationSize = 1, sequenceName = "sq_degree_table")
    private Long id;

    @NotNull
    @Column(name = "name")
    @Size(min = 4, message = "Неверная ученая степень")
    private String name;

    public AcademicDegree()
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
