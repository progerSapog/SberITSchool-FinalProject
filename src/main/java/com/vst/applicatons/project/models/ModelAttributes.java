package com.vst.applicatons.project.models;

import com.vst.applicatons.project.entity.AcademicDegree;
import com.vst.applicatons.project.entity.Cathedra;
import com.vst.applicatons.project.entity.User;

import java.util.List;

public class ModelAttributes
{
    private User user;
    private List<Cathedra> cathedraList;
    private List<AcademicDegree> academicDegreeList;

    public ModelAttributes(User user, List<Cathedra> cathedraList, List<AcademicDegree> academicDegreeList)
    {
        this.user = user;
        this.cathedraList = cathedraList;
        this.academicDegreeList = academicDegreeList;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<Cathedra> getCathedraList()
    {
        return cathedraList;
    }

    public void setCathedraList(List<Cathedra> cathedraList)
    {
        this.cathedraList = cathedraList;
    }

    public List<AcademicDegree> getAcademicDegreeList()
    {
        return academicDegreeList;
    }

    public void setAcademicDegreeList(List<AcademicDegree> academicDegreeList)
    {
        this.academicDegreeList = academicDegreeList;
    }
}
