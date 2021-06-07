package com.vst.applicatons.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer
{
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/allCathedra").setViewName("allCathedra");
        registry.addViewController("/allAcademicDegree").setViewName("allAcademicDegree");
        registry.addViewController("/allApplications").setViewName("index");
        registry.addViewController("/allUsers").setViewName("allUsers");
        registry.addViewController("/registration").setViewName("registration");
    }
}
