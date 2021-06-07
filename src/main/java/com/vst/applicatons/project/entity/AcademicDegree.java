package com.vst.applicatons.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ACADEMIC_DEGREE")
public class AcademicDegree
{
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
