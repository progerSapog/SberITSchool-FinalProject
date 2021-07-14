package com.vst.applications.project.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс объектов Кафедра - таблица БД CATHEDRA
 * Для работы с БД entity должен реализовывать getters и setters
 * для всех полей и иметь конструтор по умолчанию.
 * */
@Entity
@Table(name = "DEPARTMENT")
public class Department
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_department_table")
    @SequenceGenerator(name = "sq_department_table", allocationSize = 1, sequenceName = "sq_department_table")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    Set<User> users;

    public Department()
    {
    }

    public Department(Long id, String name)
    {
        this.id = id;
        this.name = name;
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

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }
}
