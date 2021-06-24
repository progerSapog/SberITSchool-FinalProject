package com.vst.applications.project.entity;

import javax.persistence.*;
import java.util.Set;

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

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "cathedra")
    Set<User> users;

    public Cathedra()
    {
    }

    public Cathedra(Long id, String name)
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
