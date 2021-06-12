package com.vst.applicatons.project.config;

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
     * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
    }
}
