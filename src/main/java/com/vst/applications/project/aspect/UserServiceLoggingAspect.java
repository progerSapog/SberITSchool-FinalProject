package com.vst.applications.project.aspect;

import com.vst.applications.project.entity.User;
import com.vst.applications.project.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Aspect над UserService
 * Используется для Логгирования
 *
 * @see Logger
 * @see UserService
 * */
@Component
@Aspect
public class UserServiceLoggingAspect
{
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceLoggingAspect.class);

    /**
     * After advice метода findAll
     * Логирует получение всех записей из таблицы USERS
     * */
    @After("execution(public * com.vst.applications.project.service.UserService.findAll(..))")
    public void AfterFindAll()
    {
        LOGGER.info("Получение всех записей таблицы USERS");
    }

    /**
     * Around advice метода loadUserByUsername
     * Логирует попытки аутентификации пользователя
     * */
    @Around("execution(public * com.vst.applications.project.service.UserService.loadUserByUsername(..))")
    public Object AroundLoadUserByUserName(ProceedingJoinPoint joinPoint)
    {
        Object result = null;
        Object[] args = joinPoint.getArgs();
        try
        {
            result = joinPoint.proceed();
        }
        catch (UsernameNotFoundException e)
        {
            LOGGER.warn("Ошибка аутентификации пользователя: " + args[0]);
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }

        LOGGER.info("Успешная аутентификации пользователя: " + args[0]);
        return result;
    }

    /**
     * AfterReturning advice метода addNewUser
     * Логирует добавление новой записи в таблицу USERS
     * */
    @AfterReturning(value = "execution(public * com.vst.applications.project.service.UserService.addNewUser(..))",
    returning = "boolVal")
    public void AfterReturningAddNewUser(JoinPoint joinPoint, boolean boolVal)
    {
        Object[] args = joinPoint.getArgs();
        if (boolVal)
        {
            LOGGER.info("Добавление пользователя" + ((User)args[0]).getEmail() + " в таблицу USERS");
        }
        else
        {
            LOGGER.info("При добавление пользователя" + ((User)args[0]).getEmail() + " произошла ошибка");
        }
    }

    /**
     * AfterReturning advice метода update
     * Логирует изменение записи в таблице USERS
     * */
    @AfterReturning(value = "execution(public * com.vst.applications.project.service.UserService.update(..))",
            returning = "boolVal")
    public void AfterReturningUpdate(JoinPoint joinPoint, boolean boolVal)
    {
        Object[] args = joinPoint.getArgs();
        if (boolVal)
        {
            LOGGER.info("Изменение пользователя " + ((User)args[0]).getEmail() + " прошло успешно");
        }
        else
        {
            LOGGER.info("При изменении пользователя " + ((User)args[0]).getEmail() + " произошла ошибка");
        }
    }

    /**
     * After advice метода deleteUser
     * Логгирует удаление записи из таблицы USERS
     * */
    @After("execution(public  * com.vst.applications.project.service.UserService.deleteUser(..))")
    public void AfterDeleteUser(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Удаление из таблицы USERS пользователя № " + args[0]);
    }

    /**
     * Around advice метода findById
     * Логгирует попытку получения записи из таблицы USERS
     * */
    @Around("execution(public * com.vst.applications.project.service.UserService.findById(..))")
    public Object AroundFindById(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed();

        if (((Optional<User>)result).isPresent())
        {
            LOGGER.info("Получение из таблицы USERS записи № " + args[0]);
        }
        else
        {
            LOGGER.warn("Ошибка при попытке получения из таблицы USERS записи № " + args[0]);
        }

        return result;
    }
}