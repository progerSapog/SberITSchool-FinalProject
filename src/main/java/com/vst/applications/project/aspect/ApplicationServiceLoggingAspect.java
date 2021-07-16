package com.vst.applications.project.aspect;

import com.vst.applications.project.entity.Applications;
import com.vst.applications.project.entity.Department;
import com.vst.applications.project.service.ApplicationsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceLoggingAspect.class);

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
     * Логгирует сохранение записи в таблице APPLICATIONS
     * */
    @After("execution(public  * com.vst.applications.project.service.ApplicationsService.save(..))")
    public void AfterSaveApplication(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Сохранение в таблицу записи № " + ((Applications)args[0]).getId());
    }

    /**
     * After advice метода deleteApplication
     * Логгирует удаление записи из таблицы APPLICATIONS
     * */
    @After("execution(public  * com.vst.applications.project.service.ApplicationsService.delete(..))")
    public void AfterDeleteApplication(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Удаление из таблицы записи №" + args[0]);
    }

    /**
     * Around advice метода findById
     * Логгирует нахождение записи в таблице APPLICATIONS
     * */
    @Around("execution(public * com.vst.applications.project.service.ApplicationsService.findById(..))")
    public Object AroundFindById(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed();

        if (((Optional<Applications>)result).isPresent())
        {
            LOGGER.info("Выбор записи № " + args[0] + " из таблицы Applications");
        }
        else
        {
            LOGGER.warn("Запись № " + args[0] + " из таблицы Applications не найдена");
        }

        return result;
    }
}
