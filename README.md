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
  + Spring Security-taglist 5.5.0
  + Junit
  + Hibernate-validator 6.2.0
+ UI
  + JSP (jstl 1.2)
+ Oracle DaatBase Enterprice 12.2.0.1  

### Описание сервиса
<p> Сервис предназначен для сбора заявок на установку программного обеспечения внутри института/кафедры. </p>
<p> В сервисе реализоваан система регистрации, авторизации, аутентификации и разделения ролей на Администраторов (ROLE_ADMIN) и обычных пользователей (ROLE_USER). В приложение не предусмотрено метода для регистрации пользователя-администратора. Администратора можно добавить напрямую в БД. </p>  
При помощи Spring AOP и Spring logging реализовано логирование действий сервисов. Реализовано unit тестирование сервисов.


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
Перед запуском необходимо добавить пользователя базы данных, sequence'ы и таблицы в OracleDB.  
[Скрипт для создания](https://github.com/progerSapog/SberITSchool-FinalProject/blob/main/sql%20scripts/CreateDB.sql)  
[Скрипт для удаления](https://github.com/progerSapog/SberITSchool-FinalProject/blob/main/sql%20scripts/DropDB.sql)

## Диаграмма классов
[!диаграмма классов](https://github.com/progerSapog/SberITSchool-FinalProject/blob/main/%D0%9E%D1%84%D0%BE%D1%80%D0%BC%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5/%D1%83%D0%BF%D1%80%D0%BE%D1%89%D0%B5%D0%BD%D0%BD%D0%B0%D1%8F%20%D0%B4%D0%B8%D0%B0%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B0%20%D0%BA%D0%BB%D0%B0%D1%81%D1%81%D0%BE%D0%B2.jpg)

## ER-диаграмма
[!er-diagram](https://github.com/progerSapog/SberITSchool-FinalProject/blob/main/%D0%9E%D1%84%D0%BE%D1%80%D0%BC%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5/ER-diagram.png)
