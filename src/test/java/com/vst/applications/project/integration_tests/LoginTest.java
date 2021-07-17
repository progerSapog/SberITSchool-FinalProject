package com.vst.applications.project.integration_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @RunWith - указывает окружение с которым будут запускаться тесты.
 * @SpringBootTest - запускается тестирование Spring Boot приложения.
 * @AutoConfigureMockMvc - подмена слоя MVC.
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest
{
    @Autowired
    private MockMvc mockMvc;

    /**
     * Проверка страницы index.html по адресу "/"
     * */
    @Test
    public void indexPageTest() throws Exception
    {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Войти")))
                .andExpect(content().string(containsString("Регистрация")))
                .andExpect(content().string(containsString("Заявки")));
    }

    /**
     * Тест - данные страницы требуют авторизации.
     * is3xxRedirection - перенаправление на страницу login
     * */
    @Test
    public void accessDeniedTest() throws Exception
    {
        this.mockMvc.perform(get("/application/add"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/application/update"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/user/update"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/user/update/**"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/user/save"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/user/update/**"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/department/**"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/user/all"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/user/changeRole"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    /**
     * Проверка входа с правильынми параметрами.
     * */
    @Test
    public void correctLoginTest() throws Exception
    {
        this.mockMvc.perform(post("/login")
                .param("email", "admin@gmail.com")
                .param("password", "admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    /**
     * Проверка входа с неправильынми параметрами.
     * */
    @Test
    public void badCredentialsTest() throws Exception
    {
        this.mockMvc.perform(post("/login")
                .param("email", "admin@gmail.com")
                .param("password", "aegaqwgdmin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
}
