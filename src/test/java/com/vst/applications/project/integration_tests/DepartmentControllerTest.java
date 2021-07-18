package com.vst.applications.project.integration_tests;

import com.vst.applications.project.controllers.DepartmentController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Простые интеграционные тесты свя занные с DepartmentController
 *
 * @see DepartmentController
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"CreateTestDb.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"DropTestDb.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DepartmentControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentController departmentController;

    @Test
    public void Test(){}

    /**
     * Проверка начилия контроллера DepartmentController и того,
     * что Spring его видит.
     * */
    @Test
    public void departmentController()
    {
        assertThat(departmentController).isNotNull();
    }

    /**
     * Проверка того, что обычный пользователь не имеет
     * доступа на данные старницы.
     * */
    @Test
    @WithUserDetails("Petr@gmail.com")
    public void accessDeniedForUser() throws Exception
    {
        this.mockMvc.perform(get("/department/all"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accessDenied"));

        this.mockMvc.perform(get("/department/add"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accessDenied"));

        this.mockMvc.perform(get("/department/update"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accessDenied"));

        this.mockMvc.perform(get("/department/save"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accessDenied"));
    }

    /**
     * Проверка того, что доступ к данным страницам имеет только авторизованный пользователь.
     * В противном случаек перенаправляем его на страницу /login
     * */
    @Test
    @WithAnonymousUser
    public void accessDeniedForAnonymous() throws Exception
    {
        this.mockMvc.perform(get("/department/all"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/department/add"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/department/update"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        this.mockMvc.perform(get("/department/save"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    /**
     * Проверка авторизованности пользователя.
     * Если пользователь авторизован, то на странице виден
     * специальный элемент с почтой текущего пользвателя.
     * */
    @Test
    @WithUserDetails("adminTest@gmail.com")
    public void departmentAllPageTest() throws Exception
    {
        this.mockMvc.perform(get("/department/all"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/div[1]/h3/a/label").string("adminTest@gmail.com"));
    }
}
