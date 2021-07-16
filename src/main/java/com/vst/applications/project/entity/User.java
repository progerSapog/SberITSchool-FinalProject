package com.vst.applications.project.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

/**
 * Класс объектов User - таблица БД USER
 * Для работы с БД entity должен реализовывать getters и setters
 * для всех полей и иметь конструтор по умолчанию.
 *
 * Имеет bi-directional association с entity RoleDTO
 *
 * @see Role
 * */
@Entity
@Table(name = "USERS")
public class User implements UserDetails
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_users_table")
    @SequenceGenerator(name = "sq_users_table", allocationSize = 1, sequenceName = "sq_users_table")
    private Long id;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @Transient
    private String passwordToChange;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "middle_name")
    private String middleName;

    /*  uni-directional many to one association with entity AcademicDegree */
    @ManyToOne
    @JoinColumn(name = "academic_degree_id")
    private AcademicDegree academicDegree;

    /*  uni-directional many to one association with entity Cathedra */
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    /*  bi-directional many to many association with entity RoleDTO
    JoinTable - USER_ROLE */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    /**
     * Перед удалением пользователя, удаляем его из множества
     * пользователей объекта роль.
     * */
    @PreRemove
    private void removeGroupsFromUsers()
    {
        for (Role role : roles)
        {
            role.getUsers().remove(this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return getRoles();
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User() {
    }

    public User(Long id, String email, String password, String passwordConfirm, String passwordToChange, String firstName, String lastName, String middleName, AcademicDegree academicDegree, Department department, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.passwordToChange = passwordToChange;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.academicDegree = academicDegree;
        this.department = department;
        this.roles = roles;
    }

    public User(Long id)
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPasswordConfirm()
    {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm)
    {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPasswordToChange()
    {
        return passwordToChange;
    }

    public void setPasswordToChange(String passwordToChange)
    {
        this.passwordToChange = passwordToChange;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public AcademicDegree getAcademicDegree()
    {
        return academicDegree;
    }

    public void setAcademicDegree(AcademicDegree academicDegree)
    {
        this.academicDegree = academicDegree;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }
}
