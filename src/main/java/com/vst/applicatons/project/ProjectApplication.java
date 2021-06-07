package com.vst.applicatons.project;

import com.vst.applicatons.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.List;

@SpringBootApplication
public class ProjectApplication  extends SpringBootServletInitializer
{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ProjectApplication.class);
    }

    public static void main(String[] args)
    {
        SpringApplication.run(ProjectApplication.class, args);


    }


}
