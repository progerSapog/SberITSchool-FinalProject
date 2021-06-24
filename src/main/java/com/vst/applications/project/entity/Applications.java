package com.vst.applications.project.entity;

import javax.persistence.*;

/**
 * Класс объектов заявка - таблица БД APPLICATIONS
 * Для работы с БД entity должен реализовывать getters и setters
 * для всех полей и иметь конструтор по умолчанию.
 * */
@Entity
@Table(name = "APPLICATIONS")
public class Applications
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_apl_table")
    @SequenceGenerator(name = "sq_apl_table", allocationSize = 1, sequenceName = "sq_apl_table")
    private Long id;

    @Column(name = "audience_number")
    private int audienceNumber;

    @Column(name = "text")
    private String text;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    public Applications()
    {
    }

    public Applications(Long id, int audienceNumber, String text)
    {
        this.id = id;
        this.audienceNumber = audienceNumber;
        this.text = text;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getAudienceNumber()
    {
        return audienceNumber;
    }

    public void setAudienceNumber(int audienceNumber)
    {
        this.audienceNumber = audienceNumber;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}