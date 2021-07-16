package com.vst.applications.project.DTO;

import com.vst.applications.project.entity.Role;
import com.vst.applications.project.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Data Transfer Object для entity
 * Role
 *
 * @see Role
 * */
public class RoleDTO
{
    private Long id;

    @NotNull
    @Size(min = 2, message = "Неверная роль")
    private String name;

    private Set<User> users;

    public RoleDTO()
    {
    }

    public RoleDTO(Long id, String name, Set<User> users)
    {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public RoleDTO(Long id)
    {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
