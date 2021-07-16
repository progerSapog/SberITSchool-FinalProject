package com.vst.applications.project.DTO;

import com.vst.applications.project.entity.Applications;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object для entity
 * Applications
 *
 * @see Applications
 * */
public class ApplicationsDTO
{
    private Long id;

    @NotNull
    @Digits(integer = 4, fraction = 0)
    private Integer audienceNumber;

    @NotBlank
    private String text;

    public ApplicationsDTO()
    {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAudienceNumber() {
        return audienceNumber;
    }

    public void setAudienceNumber(Integer audienceNumber) {
        this.audienceNumber = audienceNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}