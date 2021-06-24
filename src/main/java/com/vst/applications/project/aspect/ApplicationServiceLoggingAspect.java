package com.vst.applications.project.aspect;

import com.vst.applications.project.entity.Applications;
import com.vst.applications.project.service.ApplicationsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect над ApplicationsService
 * Используется для Логгирования
 *
 * @see Logger
 * @see ApplicationsService
 * */
@Component
@Aspect
public class ApplicationServiceLoggingAspect
{
    private final Logger LOGGER = LoggerFactory.getLogger(CathedraServiceLoggingAspect.class);

    /**
     * After advice метода findAll
     * Логгирует получение всех записей из таблицы
     * */
    @After("execution(public  * com.vst.applications.project.service.ApplicationsService.findAll())")
    public void AfterFindAll()
    {
        LOGGER.info("Получение всех записей таблицы APPLICATIONS");
    }

    /**
     * After advice метода saveApplication
     * Логгирует получение сохранение записи в таблице APPLICATIONS
     * */
    @After("execution(public  * com.vst.applications.project.service.ApplicationsService.saveApplication(..))")
    public void AfterSaveApplication(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Сохранение в таблицу записи № " + ((Applications)args[0]).getId());
    }

    /**
     * After advice метода deleteApplication
     * Логгирует получение удаление записи из таблицы APPLICATIONS
     * */
    @After("execution(public  * com.vst.applications.project.service.ApplicationsService.deleteApplication(..))")
    public void AfterDeleteApplication(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Удаление из таблицы записи №" + args[0]);
    }
}
