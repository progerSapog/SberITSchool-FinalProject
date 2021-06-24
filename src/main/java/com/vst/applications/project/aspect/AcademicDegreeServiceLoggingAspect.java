package com.vst.applications.project.aspect;

import com.vst.applications.project.service.AcademicDegreeService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect над AcademicDegreeService
 * Используется для Логгирования
 *
 * @see Logger
 * @see AcademicDegreeService
 * */
@Component
@Aspect
public class AcademicDegreeServiceLoggingAspect
{
    private final Logger LOGGER = LoggerFactory.getLogger(AcademicDegreeServiceLoggingAspect.class);

    /**
     * After advice метода findAll
     * Логгирует получение всех записей из таблицы
     * */
    @After("execution(public  * com.vst.applications.project.service.AcademicDegreeService.findAll())")
    public void AfterFindAll()
    {
        LOGGER.info("Получение всех записей таблицы ACADEMIC_DEGREE");
    }
}
