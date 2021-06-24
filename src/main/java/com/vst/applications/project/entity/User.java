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
 * Имеет bi-directional association с entity Role
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

    @NotBlank
    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Неверная почта")
    private String email;

    @NotBlank
    @Column(name = "password")
    @Size(min = 2, message = "Пароль должен содержать от 2 символов")
    private String password;

    @Transient
    private String passwordConfirm;

    @Transient
    private String passwordToChange;

    @NotBlank
    @Column(name = "first_name")
    @Size(min = 2, message = "Неверное имя")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    @Size(min = 2, message = "Неверная фамилия")
    private String lastName;

    @NotBlank
    @Column(name = "middle_name")
    @Size(min = 2, message = "Неверное отчество")
    private String middleName;

    /*  uni-directional many to one association with entity AcademicDegree */
    @ManyToOne
    @JoinColumn(name = "academic_degree_id")
    private AcademicDegree academicDegree;

    /*  uni-directional many to one association with entity Cathedra */
    @ManyToOne
    @JoinColumn(name = "cathedra_id")
    private Cathedra cathedra;

    /*  bi-directional many to many association with entity Role
    JoinTable - USER_ROLE */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Cathedra getCathedra()
    {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra)
    {
        this.cathedra = cathedra;
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
