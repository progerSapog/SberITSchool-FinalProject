package com.vst.applications.project.integration_tests;

import com.vst.applications.project.controllers.ApplicationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("Petr@gmail.com")
@TestPropertySource("/application-test.properties")
@Sql(value = {"CreateTestDb.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"DropTestDb.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ApplicationsControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationController applicationController;

    @Test
    public void Test(){};
//    @Test
//    public void applicationsAddPageTest() throws Exception
//    {
//        this.mockMvc.perform(get("/application/add"))
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(xpath("/html/body/div/h3/a/label").string("Petr@gmail.com"));
//    }
}
