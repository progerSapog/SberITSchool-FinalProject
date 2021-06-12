package com.vst.applicatons.project.config;

import com.vst.applicatons.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Конфигурация доступа к различным ресурсам сайта.
 * Содержит бины BCryptPasswordEncoder, который используется в RegistrationController
 *
 * @see WebSecurityConfigurerAdapter
 * @see BCryptPasswordEncoder
 * */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    //DI userService'а в данную конфигурацию
    @Autowired
    private UserService userService;

    //Создание собтсвенного бина на основе org.springframework.security.crypto.bcrypt
    //Используется для bcrypt - хэширования, которое распространнено дял хранения паролей в БД
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * Настройка доступа к разным ресурсам сайта.
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .csrf()
            .disable()
            .authorizeRequests()
                //доступ для не зарегестрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()

                //Доступ для Админов
                .antMatchers("/admin/**").hasRole("ADMIN")

                //Доступ всем
                .antMatchers("/", "/allAcademicDegree", "/allCathedra", "/index", "/allUsers").permitAll()

                //Требуют аутентификации
                .anyRequest().authenticated().and()

                //Настройка для входа в систему
                .formLogin().loginPage("/login")

                //Перенаправление на главую страницу
                .defaultSuccessUrl("/").permitAll().and()
                .logout().permitAll().logoutSuccessUrl("/");
    }

    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
