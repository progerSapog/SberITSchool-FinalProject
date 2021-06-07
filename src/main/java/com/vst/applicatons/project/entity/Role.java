package com.vst.applicatons.project.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role implements GrantedAuthority
{
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "name")
    @Size(min = 2, message = "Неверная роль")
    private String name;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    public Role()
    {
    }

    public Role(Long id)
    {
        this.id = id;
    }

    public Role(Long id, String name)
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

    @Override
    public String getAuthority()
    {
        return getName();
    }
}
