package com.vst.applications.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Для страниц, которые никак не обрабатываются сервером
 * настроиваем маппинг прямо в конфигурации.
 *
 * @see WebMvcConfigurer
 * */
@Configuration
public class MvcConfig implements WebMvcConfigurer
{

    /**
     * Добавление "простых" контроллев, не содержащих логики.
     * Добавленные контроллеры:
     *  /login        - требует лишь добавления контроллера, вся логика реализуется Spring Security
     *  /index        - главное страница, содержит только переходы на другие страницы
     *  /accessDenied - ошибка доступа, содержит ссылку на главную страницу
     * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("");
        registry.addViewController("/accessDenied").setViewName("accessDenied");
    }
}
