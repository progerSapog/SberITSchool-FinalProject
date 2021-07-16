package com.vst.applications.project.DTO;

import com.vst.applications.project.entity.AcademicDegree;
import com.vst.applications.project.entity.Department;
import com.vst.applications.project.entity.Role;
import com.vst.applications.project.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Data Transfer Object для entity
 * User
 *
 * @see User
 * */
public class UserDTO
{
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Неверная почта")
    private String email;

    @NotBlank
    @Size(min = 2, message = "Пароль должен содержать от 2 символов")
    private String password;

    private String passwordConfirm;

    private String passwordToChange;

    @NotBlank
    @Size(min = 2, message = "Неверное имя")
    private String firstName;

    @NotBlank
    @Size(min = 2, message = "Неверная фамилия")
    private String lastName;

    @NotBlank
    @Size(min = 2, message = "Неверное отчество")
    private String middleName;

    private AcademicDegree academicDegree;

    private Department department;

    private Set<Role> roles;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String password, String passwordConfirm, String passwordToChange, String firstName, String lastName, String middleName, AcademicDegree academicDegree, Department department, Set<Role> roles) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPasswordToChange() {
        return passwordToChange;
    }

    public void setPasswordToChange(String passwordToChange) {
        this.passwordToChange = passwordToChange;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(AcademicDegree academicDegree) {
        this.academicDegree = academicDegree;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
