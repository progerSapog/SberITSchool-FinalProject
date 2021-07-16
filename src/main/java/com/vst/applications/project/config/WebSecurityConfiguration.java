package com.vst.applications.project.config;

import com.vst.applications.project.handlers.MyAccessDeniedHandler;
import com.vst.applications.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Конфигурация доступа к различным ресурсам сайта.
 * Содержит бины BCryptPasswordEncoder, который используется в RegistrationController
 *
 * @see WebSecurityConfigurerAdapter
 * @see BCryptPasswordEncoder
 * */
@Configuration
@EnableWebSecurity
//@EnableAutoConfiguration
@ComponentScan
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserService userService;

    //Позвоялет получить bean на оснвое BCryptPasswordEncoder и внедрять его
    //в другие классы
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    //Позвоялет получить bean на оснвое MyAccessDeniedHandler
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new MyAccessDeniedHandler();
    }

    /**
     * Настройка доступа к разным ресурсам сайта.
     * */
    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http
            .csrf()
            .disable()
            .authorizeRequests()
                //доступ для не зарегестрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()

                //Доступ для Админов
                .antMatchers("/cathedra/**","/user/all").hasRole("ADMIN")

                //Доступ всем
                .antMatchers("/", "/applications/all").permitAll()

                //Требуют аутентификации
                .antMatchers("/applications/add", "user/update", "user/update/**", "user/save").authenticated().and()

                //Настройка для входа в систему
                .formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")

                //Перенаправление на главую страницу
                .defaultSuccessUrl("/").permitAll().and()
                .logout().permitAll().logoutSuccessUrl("/")

                //Обработка ошибки доступа
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Autowired
    protected void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
