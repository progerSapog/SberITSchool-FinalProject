package com.vst.applicatons.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "APPLICATIONS")
public class Applications
{
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "audience_number")
    @Size(max = 4, min = 4, message = "Неверная аудитория")
    private int audienceNumber;

    @NotNull
    @Column(name = "text")
    private String text;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Applications()
    {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAudienceNumber() {
        return audienceNumber;
    }

    public void setAudienceNumber(int audienceNumber) {
        this.audienceNumber = audienceNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
