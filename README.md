# SberITSchool-FinalProject
## Сервис сбора заявок на установку ПО в компьютерные классы

### Технологический стек:
+ Back-end
  + Spring-boot 2.5.0
    + AOP
    + Data-Jpa
    + Security
    + Web
    + Test
  + OrcaleDb driver
  + Spring Security-test
  + Spring Security-taglist
  + Junit
  + Hibernate-validator
+ UI
  + JSP (tomcat-embed-jasper, jstl)
+ Oracle DaatBase Enterprice 12.2.0.1  

### Описание сервиса
<p> Сервис предназначен для сбора заявок на установку программного обеспечения внутри института/кафедры. </p>
<p> В сервисе реализоваан система регистрации, авторизации, аутентификации и разделения ролей на Администраторов (ROLE_ADMIN) и обычных пользователей (ROLE_USER). В приложение не предусмотрено метода для регистрации пользователя-администратора. Администратора можно добавить напрямую в БД. </p>  
При помощи Spring AOP и Spring logging реализовано _логирование_ действий сервисов. Реализовано unit тестирование сервисов.


### Функцианал  
**Администратор**  
Администраторы могут:
+ Удалять простых пользователей
+ Создавать свои заявки
+ Удалять любые заявки
+ Создавать/изменять/удалять кафедры
+ Изменять данные своей учетной записи

**Зарегистрированные пользователи**  
Пользователи могут:
+ Создавать/удалять свои заявки
+ Изменять данные своей учтеной записи

**Незарегистрированные пользователи**  
Пользователи могут:
+ Просматривать все записи
+ Залогиниться
+ Зарегистрироваться  

### Перед запуском
Перед запуском необходимо добавить пользователя, sequence'ы и таблицы в OracleDB.  
[Скрипт для создания](https://github.com/progerSapog/SberITSchool-FinalProject/blob/main/sql%20scripts/CreateDB.sql)  
[Скрипт для удаления](https://github.com/progerSapog/SberITSchool-FinalProject/blob/main/sql%20scripts/DropDB.sql)
