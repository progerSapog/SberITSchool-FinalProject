package com.vst.applications.project.aspect;

import com.vst.applications.project.entity.Department;
import com.vst.applications.project.service.DepartmentService;
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
 * Aspect над CathedraService
 * Используется для Логгирования
 *
 * @see Logger
 * @see DepartmentService
 * */
@Component
@Aspect
public class DepartmentServiceLoggingAspect
{
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceLoggingAspect.class);

    /**
     * After advice метода findAll
     * Логгирует получение всех записей из таблицы
     * */
    @After("execution(public  * com.vst.applications.project.service.DepartmentService.findAll())")
    public void AfterFindAll()
    {
        LOGGER.info("Получение всех записей таблицы Department");
    }

    /**
     * After advice метода delete
     * Логгирует удаление записи из таблицы Department
     * */
    @After("execution(public  * com.vst.applications.project.service.DepartmentService.delete(..))")
    public void AfterDeleteCathedra(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Удаление из таблицы кафедры №" + args[0]);
    }

    /**
     * After advice метода save
     * Логгирует сохранение записи в таблице Department
     * */
    @After("execution(public  * com.vst.applications.project.service.DepartmentService.save(..))")
    public void AfterSaveCathedra(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Сохранение в таблицу кафедры " + ((Department)args[0]).getName() + " " + ((Department)args[0]).getId());
    }

    /**
     * Around advice метода findById
     * Логгирует нахождение записи в таблице Department
     * */
    @Around("execution(public * com.vst.applications.project.service.DepartmentService.findById(..))")
    public Object AroundFindById(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed();

        if (((Optional<Department>)result).isPresent())
        {
            LOGGER.info("Выбор записи № " + args[0] + " из таблицы Department");
        }
        else
        {
            LOGGER.warn("Запись № " + args[0] + " из таблицы Department не найдена");
        }

        return result;
    }
}
