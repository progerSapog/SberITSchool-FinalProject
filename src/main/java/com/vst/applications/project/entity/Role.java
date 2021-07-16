package com.vst.applications.project.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Класс объектов RoleDTO - таблица БД ROLE
 * Для работы с БД entity должен реализовывать getters и setters
 * для всех полей и иметь конструтор по умолчанию.
 *
 * Имеет bi-directional association с entity User
 *
 * @see User
 * */
@Entity
@Table(name = "ROLE")
public class Role implements GrantedAuthority
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_role_table")
    @SequenceGenerator(name = "sq_role_table", allocationSize = 1, sequenceName = "sq_role_table")
    private Long id;

    @Column(name = "name")
    private String name;

    /*  bi-directional association с entity User
        JoinTable - USER_ROLE */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Role()
    {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(Long id)
    {
        this.id = id;
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

    @Override
    public String getAuthority()
    {
        return getName();
    }
}
